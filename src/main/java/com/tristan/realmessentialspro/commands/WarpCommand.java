package com.tristan.realmessentialspro.commands;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class WarpCommand extends BaseCommand implements CommandExecutor {
    public WarpCommand(RealmEssentialsPro plugin) { super(plugin); }
    @Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = requirePlayer(sender); if (player == null) return true;
        if (args.length == 0) {
            plugin.getGuiManager().openWarps(player);
            return true;
        }
        var loc = plugin.getWarpManager().getWarp(args[0]);
        if (loc == null) { plugin.getMessageManager().send(player, "warp-missing"); return true; }
        plugin.getTeleportManager().teleport(player, loc, () -> plugin.getMessageManager().send(player, "teleported-warp", "%name%", args[0]));
        return true;
    }
}
