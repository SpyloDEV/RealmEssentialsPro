package com.tristan.realmessentialspro.managers;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import com.tristan.realmessentialspro.data.DataStore;
import com.tristan.realmessentialspro.util.LocationUtil;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.*;

public class HomeManager {
    private final RealmEssentialsPro plugin;
    private final DataStore dataStore;

    public HomeManager(RealmEssentialsPro plugin, DataStore dataStore) {
        this.plugin = plugin;
        this.dataStore = dataStore;
    }

    public int getMaxHomes() {
        return plugin.getConfig().getInt("settings.max-homes", 3);
    }

    public boolean setHome(UUID uuid, String name, Location location) {
        name = name.toLowerCase(Locale.ROOT);
        var config = dataStore.getHomesConfig();
        String base = uuid + "." + name;
        if (!config.contains(base) && getHomes(uuid).size() >= getMaxHomes()) {
            return false;
        }
        config.set(base, null);
        LocationUtil.saveLocation(config, base, location);
        dataStore.save(config, dataStore.getHomesFile());
        return true;
    }

    public Location getHome(UUID uuid, String name) {
        ConfigurationSection section = dataStore.getHomesConfig().getConfigurationSection(uuid + "." + name.toLowerCase(Locale.ROOT));
        return LocationUtil.readLocation(section);
    }

    public boolean deleteHome(UUID uuid, String name) {
        var config = dataStore.getHomesConfig();
        String path = uuid + "." + name.toLowerCase(Locale.ROOT);
        if (!config.contains(path)) return false;
        config.set(path, null);
        dataStore.save(config, dataStore.getHomesFile());
        return true;
    }

    public Set<String> getHomes(UUID uuid) {
        ConfigurationSection section = dataStore.getHomesConfig().getConfigurationSection(uuid.toString());
        if (section == null) return new TreeSet<>();
        return new TreeSet<>(section.getKeys(false));
    }
}
