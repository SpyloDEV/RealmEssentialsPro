package com.tristan.realmessentialspro.commands;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

public class RepairCommand extends BaseCommand implements CommandExecutor {
    public RepairCommand(RealmEssentialsPro plugin) { super(plugin); }
    @Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPermission(sender, "rep.repair")) return true;
        Player player = requirePlayer(sender); if (player == null) return true;
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null || !(item.getItemMeta() instanceof Damageable meta)) {
            plugin.getMessageManager().send(player, "repair-invalid"); return true;
        }
        meta.setDamage(0); item.setItemMeta(meta);
        plugin.getMessageManager().send(player, "repair-success");
        return true;
    }
}
