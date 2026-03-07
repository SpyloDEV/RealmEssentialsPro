package com.tristan.realmessentialspro.managers;

import com.tristan.realmessentialspro.data.DataStore;
import com.tristan.realmessentialspro.util.LocationUtil;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.*;

public class WarpManager {
    private final DataStore dataStore;

    public WarpManager(Object plugin, DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void setWarp(String name, Location location) {
        name = name.toLowerCase(Locale.ROOT);
        var config = dataStore.getWarpsConfig();
        config.set(name, null);
        LocationUtil.saveLocation(config, name, location);
        dataStore.save(config, dataStore.getWarpsFile());
    }

    public Location getWarp(String name) {
        ConfigurationSection section = dataStore.getWarpsConfig().getConfigurationSection(name.toLowerCase(Locale.ROOT));
        return LocationUtil.readLocation(section);
    }

    public boolean deleteWarp(String name) {
        String path = name.toLowerCase(Locale.ROOT);
        var config = dataStore.getWarpsConfig();
        if (!config.contains(path)) return false;
        config.set(path, null);
        dataStore.save(config, dataStore.getWarpsFile());
        return true;
    }

    public Set<String> getWarps() {
        return new TreeSet<>(dataStore.getWarpsConfig().getKeys(false));
    }
}
