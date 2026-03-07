package com.tristan.realmessentialspro.commands;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class SpawnCommand extends BaseCommand implements CommandExecutor {
    public SpawnCommand(RealmEssentialsPro plugin) { super(plugin); }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = requirePlayer(sender);
        if (player == null) return true;
        var spawn = plugin.getSpawnManager().getSpawn();
        if (spawn == null) return true;
        plugin.getTeleportManager().teleport(player, spawn, () -> plugin.getMessageManager().send(player, "teleported-spawn"));
        return true;
    }
}
