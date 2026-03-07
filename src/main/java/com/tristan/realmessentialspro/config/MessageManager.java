package com.tristan.realmessentialspro.config;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import com.tristan.realmessentialspro.util.ColorUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MessageManager {

    private final RealmEssentialsPro plugin;
    private File file;
    private FileConfiguration config;

    public MessageManager(RealmEssentialsPro plugin) {
        this.plugin = plugin;
        reload();
    }

    public void reload() {
        file = new File(plugin.getDataFolder(), "messages.yml");
        if (!file.exists()) {
            plugin.saveResource("messages.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public String get(String path) {
        return ColorUtil.color(config.getString(path, "&cMissing message: " + path));
    }

    public String format(String path, String... replacements) {
        String message = get(path);
        for (int i = 0; i + 1 < replacements.length; i += 2) {
            message = message.replace(replacements[i], replacements[i + 1]);
        }
        return message;
    }

    public String prefix() {
        return get("prefix");
    }

    public void send(CommandSender sender, String path) {
        sender.sendMessage(prefix() + get(path));
    }

    public void send(CommandSender sender, String path, String... replacements) {
        sender.sendMessage(prefix() + format(path, replacements));
    }
}
