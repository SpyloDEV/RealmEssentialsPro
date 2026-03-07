package com.tristan.realmessentialspro.managers;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeleportManager {
    private final RealmEssentialsPro plugin;
    private final Map<UUID, Location> warmupTargets = new HashMap<>();
    private final Map<UUID, BukkitTask> tasks = new HashMap<>();
    private final Map<UUID, Location> startingLocations = new HashMap<>();

    public TeleportManager(RealmEssentialsPro plugin) {
        this.plugin = plugin;
    }

    public void teleport(Player player, Location target, Runnable after) {
        boolean useWarmup = plugin.getConfig().getBoolean("settings.use-teleport-warmup", true);
        int seconds = plugin.getConfig().getInt("settings.teleport-warmup-seconds", 3);

        if (!useWarmup || seconds <= 0) {
            player.teleport(target);
            if (after != null) after.run();
            return;
        }

        cancel(player);
        startingLocations.put(player.getUniqueId(), player.getLocation().clone());
        warmupTargets.put(player.getUniqueId(), target);
        plugin.getMessageManager().send(player, "teleport-warmup", "%seconds%", String.valueOf(seconds));

        BukkitTask task = plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
            if (!warmupTargets.containsKey(player.getUniqueId())) return;
            player.teleport(target);
            warmupTargets.remove(player.getUniqueId());
            startingLocations.remove(player.getUniqueId());
            tasks.remove(player.getUniqueId());
            if (after != null) after.run();
        }, seconds * 20L);

        tasks.put(player.getUniqueId(), task);
    }

    public void cancel(Player player) {
        UUID uuid = player.getUniqueId();
        BukkitTask task = tasks.remove(uuid);
        if (task != null) task.cancel();
        warmupTargets.remove(uuid);
        startingLocations.remove(uuid);
    }

    public boolean hasWarmup(Player player) {
        return warmupTargets.containsKey(player.getUniqueId());
    }

    public Location getStartingLocation(Player player) {
        return startingLocations.get(player.getUniqueId());
    }
}
