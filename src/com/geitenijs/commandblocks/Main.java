package com.geitenijs.commandblocks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    static Main plugin;
    public final static String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

    public void onEnable() {
        Main.plugin = this;
        Hooks.registerHooks();
        Utilities.createConfigs();
        Utilities.registerCommandsAndCompletions();
        Utilities.registerEvents();
        Utilities.startTasks();
        Utilities.startMetrics();
        Utilities.pluginBanner();
        Utilities.done();
    }

    public void onDisable() {
        Utilities.stopTasks();
        Utilities.reloadConfigFile();
        Utilities.saveConfigFile();
        Utilities.reloadBlocksFile();
        Utilities.saveBlocksFile();
    }
}