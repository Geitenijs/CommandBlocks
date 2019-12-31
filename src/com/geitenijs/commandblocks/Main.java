package com.geitenijs.commandblocks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    static Main plugin;
    public final static String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

    public void onEnable() {
        Main.plugin = this;
        if (version.contains("v1_15_R1") || version.contains("v1_14_R1") || version.contains("v1_13_R2") || version.contains("v1_13_R1")) {
            Utilities.startupText();
            Hooks.registerHooks();
            Utilities.createConfigs();
            Utilities.registerCommandsAndCompletions();
            Utilities.registerEvents();
            Utilities.startSchedulers();
            Utilities.startMetrics();
            Utilities.done();
        } else {
            Utilities.errorText();
            Utilities.consoleMsg("&cYour server version is not compatible with this release of " + Strings.PLUGIN + ". Supported versions are: 1.15, 1.14 and 1.13. You can download a different release at: " + Strings.WEBSITE);
            getServer().getPluginManager().disablePlugin(Main.plugin);
        }
    }

    public void onDisable() {
        Utilities.reloadConfigFile();
        Utilities.saveConfigFile();
        Utilities.reloadBlocksFile();
        Utilities.saveBlocksFile();
        Utilities.stopSchedulers();
    }
}