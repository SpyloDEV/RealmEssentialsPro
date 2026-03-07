package com.tristan.realmessentialspro.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

public class LocationUtil {

    public static void saveLocation(ConfigurationSection section, String path, Location location) {
        ConfigurationSection target = section.createSection(path);
        target.set("world", location.getWorld().getName());
        target.set("x", location.getX());
        target.set("y", location.getY());
        target.set("z", location.getZ());
        target.set("yaw", location.getYaw());
        target.set("pitch", location.getPitch());
    }

    public static Location readLocation(ConfigurationSection section) {
        if (section == null) {
            return null;
        }
        World world = Bukkit.getWorld(section.getString("world"));
        if (world == null) {
            return null;
        }
        return new Location(
                world,
                section.getDouble("x"),
                section.getDouble("y"),
                section.getDouble("z"),
                (float) section.getDouble("yaw"),
                (float) section.getDouble("pitch")
        );
    }
}
