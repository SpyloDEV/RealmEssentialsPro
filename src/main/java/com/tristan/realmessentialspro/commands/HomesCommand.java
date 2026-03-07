package com.tristan.realmessentialspro.commands;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class HomesCommand extends BaseCommand implements CommandExecutor {
    public HomesCommand(RealmEssentialsPro plugin) { super(plugin); }
    @Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = requirePlayer(sender); if (player == null) return true;
        plugin.getGuiManager().openHomes(player);
        return true;
    }
}
