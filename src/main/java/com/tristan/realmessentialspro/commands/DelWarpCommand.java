package com.tristan.realmessentialspro.commands;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import org.bukkit.command.*;

public class DelWarpCommand extends BaseCommand implements CommandExecutor {
    public DelWarpCommand(RealmEssentialsPro plugin) { super(plugin); }
    @Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPermission(sender, "rep.admin.warp")) return true;
        if (args.length == 0) return false;
        if (!plugin.getWarpManager().deleteWarp(args[0])) {
            plugin.getMessageManager().send(sender, "warp-missing"); return true;
        }
        plugin.getMessageManager().send(sender, "warp-deleted", "%name%", args[0]);
        return true;
    }
}
