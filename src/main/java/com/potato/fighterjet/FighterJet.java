package com.potato.fighterjet;

import org.bukkit.plugin.java.JavaPlugin;

public final class FighterJet extends JavaPlugin {

    public static FighterJet instance;

    @Override
    public void onEnable() {
        instance = this;

        // Save the default config if it doesn't exist
        saveDefaultConfig();

        // Register commands, listeners, etc.
        getLogger().info("FighterJet has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("FighterJet has been disabled!");
        // Save the config
        saveConfig();
    }

    //Create a method to get main instance
    public static FighterJet getInstance() {
        return instance;
    }
}
