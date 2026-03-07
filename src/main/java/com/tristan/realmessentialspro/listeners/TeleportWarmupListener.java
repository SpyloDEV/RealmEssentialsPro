package com.tristan.realmessentialspro.listeners;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class TeleportWarmupListener implements Listener {
    private final RealmEssentialsPro plugin;

    public TeleportWarmupListener(RealmEssentialsPro plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!plugin.getConfig().getBoolean("settings.cancel-on-move", true)) return;
        Player player = event.getPlayer();
        if (!plugin.getTeleportManager().hasWarmup(player)) return;
        Location from = plugin.getTeleportManager().getStartingLocation(player);
        Location to = event.getTo();
        if (from == null || to == null) return;

        boolean moved = from.getBlockX() != to.getBlockX()
                || from.getBlockY() != to.getBlockY()
                || from.getBlockZ() != to.getBlockZ();

        if (moved) {
            plugin.getTeleportManager().cancel(player);
            plugin.getMessageManager().send(player, "teleport-cancelled-move");
        }
    }
}
