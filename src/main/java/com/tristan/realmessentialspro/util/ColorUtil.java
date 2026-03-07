package com.tristan.realmessentialspro.util;

import org.bukkit.ChatColor;

public class ColorUtil {
    public static String color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
