package com.tristan.realmessentialspro.commands;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class FlyCommand extends BaseCommand implements CommandExecutor {
    public FlyCommand(RealmEssentialsPro plugin) { super(plugin); }
    @Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPermission(sender, "rep.fly")) return true;
        Player player = requirePlayer(sender); if (player == null) return true;
        boolean enable = !player.getAllowFlight();
        player.setAllowFlight(enable);
        player.setFlying(enable);
        plugin.getMessageManager().send(player, enable ? "fly-enabled" : "fly-disabled");
        return true;
    }
}
