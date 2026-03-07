package com.tristan.realmessentialspro.commands;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class FeedCommand extends BaseCommand implements CommandExecutor {
    public FeedCommand(RealmEssentialsPro plugin) { super(plugin); }
    @Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPermission(sender, "rep.feed")) return true;
        Player target = args.length > 0 ? Bukkit.getPlayerExact(args[0]) : requirePlayer(sender);
        if (target == null) { plugin.getMessageManager().send(sender, "invalid-player"); return true; }
        target.setFoodLevel(20); target.setSaturation(20f);
        if (target.equals(sender)) plugin.getMessageManager().send(sender, "feed-self");
        else plugin.getMessageManager().send(sender, "feed-other", "%target%", target.getName());
        return true;
    }
}
