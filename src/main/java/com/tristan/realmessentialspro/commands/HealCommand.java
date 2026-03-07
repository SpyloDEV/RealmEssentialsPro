package com.tristan.realmessentialspro.commands;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class HealCommand extends BaseCommand implements CommandExecutor {
    public HealCommand(RealmEssentialsPro plugin) { super(plugin); }
    @Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPermission(sender, "rep.heal")) return true;
        Player target = args.length > 0 ? Bukkit.getPlayerExact(args[0]) : requirePlayer(sender);
        if (target == null) { plugin.getMessageManager().send(sender, "invalid-player"); return true; }
        double max = target.getAttribute(Attribute.MAX_HEALTH) != null ? target.getAttribute(Attribute.MAX_HEALTH).getValue() : 20.0;
        target.setHealth(max);
        if (target.equals(sender)) plugin.getMessageManager().send(sender, "heal-self");
        else plugin.getMessageManager().send(sender, "heal-other", "%target%", target.getName());
        return true;
    }
}
