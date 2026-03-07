package com.tristan.realmessentialspro.commands;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class BaseCommand {
    protected final RealmEssentialsPro plugin;

    protected BaseCommand(RealmEssentialsPro plugin) {
        this.plugin = plugin;
    }

    protected boolean checkPermission(CommandSender sender, String permission) {
        if (permission == null || permission.isBlank() || sender.hasPermission(permission)) return true;
        plugin.getMessageManager().send(sender, "no-permission");
        return false;
    }

    protected Player requirePlayer(CommandSender sender) {
        if (!(sender instanceof Player player)) {
            plugin.getMessageManager().send(sender, "players-only");
            return null;
        }
        return player;
    }
}
