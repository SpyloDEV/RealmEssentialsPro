package com.tristan.realmessentialspro.commands;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import com.tristan.realmessentialspro.models.TeleportRequest;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class TpaResponseCommand extends BaseCommand implements CommandExecutor {
    private final boolean accept;
    public TpaResponseCommand(RealmEssentialsPro plugin, boolean accept) { super(plugin); this.accept = accept; }
    @Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = requirePlayer(sender); if (player == null) return true;
        if (args.length == 0) return false;
        Player senderPlayer = Bukkit.getPlayerExact(args[0]);
        if (senderPlayer == null) { plugin.getMessageManager().send(player, "invalid-player"); return true; }
        TeleportRequest request = plugin.getRequestManager().getRequest(player.getUniqueId(), senderPlayer.getUniqueId());
        if (request == null) { plugin.getMessageManager().send(player, "tpa-none"); return true; }
        plugin.getRequestManager().removeRequest(player.getUniqueId(), senderPlayer.getUniqueId());
        if (!accept) {
            plugin.getMessageManager().send(player, "tpa-denied-target");
            plugin.getMessageManager().send(senderPlayer, "tpa-denied-sender");
            return true;
        }
        plugin.getMessageManager().send(player, "tpa-accepted-target");
        plugin.getMessageManager().send(senderPlayer, "tpa-accepted-sender");
        if (request.isHere()) {
            plugin.getTeleportManager().teleport(player, senderPlayer.getLocation(), null);
        } else {
            plugin.getTeleportManager().teleport(senderPlayer, player.getLocation(), null);
        }
        return true;
    }
}
