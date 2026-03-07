package com.tristan.realmessentialspro.listeners;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerConnectionListener implements Listener {

    private final RealmEssentialsPro plugin;

    public PlayerConnectionListener(RealmEssentialsPro plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        var playersConfig = plugin.getDataStore().getPlayersConfig();
        String path = player.getUniqueId() + ".joined";
        boolean firstJoin = !playersConfig.getBoolean(path, false);

        if (firstJoin) {
            playersConfig.set(path, true);
            plugin.getDataStore().save(playersConfig, plugin.getDataStore().getPlayersFile());

            if (plugin.getConfig().getBoolean("join.teleport-to-spawn-on-first-join", true)) {
                var spawn = plugin.getSpawnManager().getSpawn();
                if (spawn != null) {
                    player.teleport(spawn);
                }
            }

            if (plugin.getConfig().getBoolean("join.starter-items.enabled", true)) {
                ConfigurationSection section = plugin.getConfig().getConfigurationSection("join.starter-items");
                if (section != null) {
                    for (Object raw : section.getMapList("items")) {
                        if (raw instanceof java.util.Map<?, ?> map) {
                            String materialName = String.valueOf(map.getOrDefault("material", "STONE_SWORD"));
                            int amount = Integer.parseInt(String.valueOf(map.getOrDefault("amount", 1)));
                            Material material = Material.matchMaterial(materialName);
                            if (material != null) {
                                player.getInventory().addItem(new ItemStack(material, amount));
                            }
                        }
                    }
                }
                plugin.getMessageManager().send(player, "first-join-kit");
            }
        }
    }
}
