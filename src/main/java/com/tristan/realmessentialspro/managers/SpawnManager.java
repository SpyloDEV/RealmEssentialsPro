package com.tristan.realmessentialspro.managers;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import com.tristan.realmessentialspro.data.DataStore;
import com.tristan.realmessentialspro.util.LocationUtil;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

public class SpawnManager {
    private final RealmEssentialsPro plugin;
    private final DataStore dataStore;

    public SpawnManager(RealmEssentialsPro plugin, DataStore dataStore) {
        this.plugin = plugin;
        this.dataStore = dataStore;
    }

    public void setSpawn(Location location) {
        var config = dataStore.getSpawnConfig();
        config.set("spawn", null);
        LocationUtil.saveLocation(config, "spawn", location);
        dataStore.save(config, dataStore.getSpawnFile());
    }

    public Location getSpawn() {
        ConfigurationSection section = dataStore.getSpawnConfig().getConfigurationSection("spawn");
        return LocationUtil.readLocation(section);
    }
}
