package com.tristan.realmessentialspro.commands;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import org.bukkit.command.*;

public class RepCommand extends BaseCommand implements CommandExecutor {
    public RepCommand(RealmEssentialsPro plugin) { super(plugin); }
    @Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPermission(sender, "rep.admin.reload")) return true;
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            plugin.reloadPlugin();
            plugin.getMessageManager().send(sender, "config-reloaded");
            return true;
        }
        sender.sendMessage("/rep reload");
        return true;
    }
}
