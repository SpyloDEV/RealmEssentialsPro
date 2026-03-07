package com.tristan.realmessentialspro.commands;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class SetWarpCommand extends BaseCommand implements CommandExecutor {
    public SetWarpCommand(RealmEssentialsPro plugin) { super(plugin); }
    @Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPermission(sender, "rep.admin.warp")) return true;
        Player player = requirePlayer(sender); if (player == null) return true;
        if (args.length == 0) return false;
        plugin.getWarpManager().setWarp(args[0], player.getLocation());
        plugin.getMessageManager().send(player, "warp-set", "%name%", args[0]);
        return true;
    }
}
