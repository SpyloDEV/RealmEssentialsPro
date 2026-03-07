package com.tristan.realmessentialspro.commands;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class SetSpawnCommand extends BaseCommand implements CommandExecutor {
    public SetSpawnCommand(RealmEssentialsPro plugin) { super(plugin); }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPermission(sender, "rep.admin.spawn")) return true;
        Player player = requirePlayer(sender);
        if (player == null) return true;
        plugin.getSpawnManager().setSpawn(player.getLocation());
        plugin.getMessageManager().send(player, "spawn-set");
        return true;
    }
}
