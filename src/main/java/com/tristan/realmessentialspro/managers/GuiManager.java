package com.tristan.realmessentialspro.managers;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GuiManager {
    public static final String HOMES_TITLE = "§bYour Homes";
    public static final String WARPS_TITLE = "§dServer Warps";

    private final RealmEssentialsPro plugin;
    private final HomeManager homeManager;
    private final WarpManager warpManager;

    public GuiManager(RealmEssentialsPro plugin, HomeManager homeManager, WarpManager warpManager) {
        this.plugin = plugin;
        this.homeManager = homeManager;
        this.warpManager = warpManager;
    }

    public void openHomes(Player player) {
        int size = plugin.getConfig().getInt("settings.homes-gui-size", 27);
        Inventory inventory = Bukkit.createInventory(null, normalizeSize(size), HOMES_TITLE);
        fillHomes(player, inventory, homeManager.getHomes(player.getUniqueId()));
        player.openInventory(inventory);
    }

    public void openWarps(Player player) {
        int size = plugin.getConfig().getInt("settings.warp-gui-size", 27);
        Inventory inventory = Bukkit.createInventory(null, normalizeSize(size), WARPS_TITLE);
        fillWarps(inventory, warpManager.getWarps());
        player.openInventory(inventory);
    }

    private void fillHomes(Player player, Inventory inventory, Set<String> homes) {
        if (homes.isEmpty()) {
            inventory.setItem(13, createItem(Material.BARRIER, plugin.getMessageManager().get("gui-no-entries")));
            return;
        }
        for (String home : homes) {
            inventory.addItem(createItem(Material.RED_BED, "§a" + home, "§7Click to teleport"));
        }
    }

    private void fillWarps(Inventory inventory, Set<String> warps) {
        if (warps.isEmpty()) {
            inventory.setItem(13, createItem(Material.BARRIER, plugin.getMessageManager().get("gui-no-entries")));
            return;
        }
        for (String warp : warps) {
            inventory.addItem(createItem(Material.ENDER_PEARL, "§d" + warp, "§7Click to teleport"));
        }
    }

    private ItemStack createItem(Material material, String name, String... loreLines) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            if (loreLines.length > 0) {
                List<String> lore = new ArrayList<>(List.of(loreLines));
                meta.setLore(lore);
            }
            item.setItemMeta(meta);
        }
        return item;
    }

    private int normalizeSize(int requested) {
        int size = Math.max(9, requested);
        if (size % 9 != 0) {
            size += 9 - (size % 9);
        }
        return Math.min(size, 54);
    }
}
