package com.tristan.realmessentialspro.gui;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import com.tristan.realmessentialspro.managers.GuiManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GuiListener implements Listener {
    private final RealmEssentialsPro plugin;

    public GuiListener(RealmEssentialsPro plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;
        String title = event.getView().getTitle();
        if (!title.equals(GuiManager.HOMES_TITLE) && !title.equals(GuiManager.WARPS_TITLE)) return;
        event.setCancelled(true);

        ItemStack clicked = event.getCurrentItem();
        if (clicked == null || clicked.getType() == Material.AIR || !clicked.hasItemMeta()) return;
        String name = clicked.getItemMeta().getDisplayName().replace("§a", "").replace("§d", "");

        if (title.equals(GuiManager.HOMES_TITLE)) {
            var loc = plugin.getHomeManager().getHome(player.getUniqueId(), name);
            if (loc != null) {
                plugin.getTeleportManager().teleport(player, loc, () ->
                        plugin.getMessageManager().send(player, "teleported-home", "%name%", name));
                player.closeInventory();
            }
        } else if (title.equals(GuiManager.WARPS_TITLE)) {
            var loc = plugin.getWarpManager().getWarp(name);
            if (loc != null) {
                plugin.getTeleportManager().teleport(player, loc, () ->
                        plugin.getMessageManager().send(player, "teleported-warp", "%name%", name));
                player.closeInventory();
            }
        }
    }
}
