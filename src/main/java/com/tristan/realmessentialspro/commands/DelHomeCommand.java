package com.tristan.realmessentialspro.commands;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class DelHomeCommand extends BaseCommand implements CommandExecutor {
    public DelHomeCommand(RealmEssentialsPro plugin) { super(plugin); }
    @Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = requirePlayer(sender); if (player == null) return true;
        String name = args.length > 0 ? args[0] : "home";
        if (!plugin.getHomeManager().deleteHome(player.getUniqueId(), name)) {
            plugin.getMessageManager().send(player, "home-missing"); return true;
        }
        plugin.getMessageManager().send(player, "home-deleted", "%name%", name);
        return true;
    }
}
