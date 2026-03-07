package com.tristan.realmessentialspro.commands;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class HomeCommand extends BaseCommand implements CommandExecutor {
    public HomeCommand(RealmEssentialsPro plugin) { super(plugin); }
    @Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = requirePlayer(sender); if (player == null) return true;
        String name = args.length > 0 ? args[0] : "home";
        var loc = plugin.getHomeManager().getHome(player.getUniqueId(), name);
        if (loc == null) { plugin.getMessageManager().send(player, "home-missing"); return true; }
        plugin.getTeleportManager().teleport(player, loc, () -> plugin.getMessageManager().send(player, "teleported-home", "%name%", name));
        return true;
    }
}
