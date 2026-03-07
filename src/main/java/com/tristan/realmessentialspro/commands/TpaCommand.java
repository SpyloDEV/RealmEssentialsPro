package com.tristan.realmessentialspro.commands;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import com.tristan.realmessentialspro.models.TeleportRequest;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class TpaCommand extends BaseCommand implements CommandExecutor {
    private final boolean here;
    public TpaCommand(RealmEssentialsPro plugin, boolean here) { super(plugin); this.here = here; }
    @Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = requirePlayer(sender); if (player == null) return true;
        if (args.length == 0) return false;
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) { plugin.getMessageManager().send(player, "invalid-player"); return true; }
        if (target.equals(player)) return true;
        plugin.getRequestManager().addRequest(new TeleportRequest(player.getUniqueId(), target.getUniqueId(), here));
        plugin.getMessageManager().send(player, "tpa-sent", "%target%", target.getName());
        plugin.getMessageManager().send(target, here ? "tpahere-received" : "tpa-received", "%sender%", player.getName());
        return true;
    }
}
