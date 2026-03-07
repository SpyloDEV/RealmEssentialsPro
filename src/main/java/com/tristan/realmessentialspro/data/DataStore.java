package com.tristan.realmessentialspro.data;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DataStore {

    private final RealmEssentialsPro plugin;
    private File homesFile;
    private File warpsFile;
    private File playersFile;
    private File spawnFile;
    private FileConfiguration homesConfig;
    private FileConfiguration warpsConfig;
    private FileConfiguration playersConfig;
    private FileConfiguration spawnConfig;

    public DataStore(RealmEssentialsPro plugin) {
        this.plugin = plugin;
        reloadAll();
    }

    public void reloadAll() {
        homesFile = createFile("homes.yml");
        warpsFile = createFile("warps.yml");
        playersFile = createFile("players.yml");
        spawnFile = createFile("spawn.yml");

        homesConfig = YamlConfiguration.loadConfiguration(homesFile);
        warpsConfig = YamlConfiguration.loadConfiguration(warpsFile);
        playersConfig = YamlConfiguration.loadConfiguration(playersFile);
        spawnConfig = YamlConfiguration.loadConfiguration(spawnFile);
    }

    private File createFile(String name) {
        File file = new File(plugin.getDataFolder(), name);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Could not create file: " + name, e);
            }
        }
        return file;
    }

    public void saveAll() {
        save(homesConfig, homesFile);
        save(warpsConfig, warpsFile);
        save(playersConfig, playersFile);
        save(spawnConfig, spawnFile);
    }

    public void save(FileConfiguration config, File file) {
        try {
            config.save(file);
        } catch (IOException e) {
            plugin.getLogger().warning("Could not save file " + file.getName());
            e.printStackTrace();
        }
    }

    public FileConfiguration getHomesConfig() { return homesConfig; }
    public FileConfiguration getWarpsConfig() { return warpsConfig; }
    public FileConfiguration getPlayersConfig() { return playersConfig; }
    public FileConfiguration getSpawnConfig() { return spawnConfig; }
    public File getHomesFile() { return homesFile; }
    public File getWarpsFile() { return warpsFile; }
    public File getPlayersFile() { return playersFile; }
    public File getSpawnFile() { return spawnFile; }
}
