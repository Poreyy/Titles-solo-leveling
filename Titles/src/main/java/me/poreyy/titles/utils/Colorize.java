package me.poreyy.titles.utils;

import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;

public class Colorize {

    private final static Map<String, String> CACHED_MESSAGES = new HashMap<>();

    public static String cachedColorize(String text) {
        return CACHED_MESSAGES.computeIfAbsent(text, Colorize::colorize);
    }

    public static String colorize(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}