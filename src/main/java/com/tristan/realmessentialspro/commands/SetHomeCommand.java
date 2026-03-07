package com.tristan.realmessentialspro.commands;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class SetHomeCommand extends BaseCommand implements CommandExecutor {
    public SetHomeCommand(RealmEssentialsPro plugin) { super(plugin); }
    @Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = requirePlayer(sender); if (player == null) return true;
        String name = args.length > 0 ? args[0] : "home";
        boolean success = plugin.getHomeManager().setHome(player.getUniqueId(), name, player.getLocation());
        if (!success) { plugin.getMessageManager().send(player, "home-limit"); return true; }
        plugin.getMessageManager().send(player, "home-set", "%name%", name);
        return true;
    }
}
