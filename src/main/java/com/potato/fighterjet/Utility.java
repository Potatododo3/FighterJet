package com.potato.fighterjet;

import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Utility {

    private static final FighterJet instance = FighterJet.getInstance();
    private static final Pattern pattern = Pattern.compile("&#[a-fA-F0-9]{6}");
    // Color Utility Methods
    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String translate(String input) {
        Matcher match = pattern.matcher(input);
        while(match.find()){
            String color = input.substring(match.start()+1, match.end());
            input = input.replace("&" + color, net.md_5.bungee.api.ChatColor.of(color) + "");
            match = pattern.matcher(input);
        }
        return input.replace("&", "§");
    }

    public static List<String> translate(List<String> input) {
        return (List<String>)input.stream().map(Utility::translate).collect(Collectors.toList());
    }
    public static String[] color(String... messages) {
        for (int i = 0; i < messages.length; i++) {
            messages[i] = color(messages[i]);
        }
        return messages;
    }

    // Lore Utility Methods
    public static void addLore(ItemStack item, String... loreLines) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
            if (lore != null) {
                for (String line : loreLines) {
                    lore.add(color(line));
                }
                meta.setLore(lore);
                item.setItemMeta(meta);
            }
        }
    }

    public static void setLore(ItemStack item, List<String> loreLines) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            List<String> lore = new ArrayList<>();
            for (String line : loreLines) {
                lore.add(color(line));
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
    }

    // Configuration Utility Methods
    public static String getConfigString(String path) {
        return color(instance.getConfig().getString(path));
    }

    public static int getConfigInt(String path) {
        return instance.getConfig().getInt(path);
    }

    public static boolean getConfigBoolean(String path) {
        return instance.getConfig().getBoolean(path);
    }

    public static void setConfigValue(String path, Object value) {
        FileConfiguration config = instance.getConfig();
        config.set(path, value);
        instance.saveConfig();
    }

    public static void reloadConfig() {
        instance.reloadConfig();
    }
}