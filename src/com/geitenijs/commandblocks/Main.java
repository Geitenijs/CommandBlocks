package com.geitenijs.commandblocks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    static Main plugin;
    public final static String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

    public void onEnable() {
        Main.plugin = this;
        if ((version.contains("v1_14_R1") ||version.contains("v1_13_R2") || version.contains("v1_13_R1") || version.contains("v1_12_R1") || version.contains("v1_11_R1") || version.contains("v1_10_R1") || version.contains("v1_9_R2") || version.contains("v1_9_R1") || version.contains("v1_8_R3") || version.contains("v1_8_R2") || version.contains("v1_8_R1"))) {
            Utilities.startupText();
            Utilities.createConfigs();
            Utilities.registerCommandsAndCompletions();
            Utilities.registerEvents();
            Utilities.startSchedulers();
            Utilities.startMetrics();
            Utilities.done();
        } else {
            Utilities.errorText();
            Utilities.consoleMsgPrefixed("§cYour server version is not compatible with this release of " + Strings.PLUGIN + ". Supported versions are: 1.14, 1.13, 1.12, 1.11, 1.10, 1.9 and 1.8. You can download a different release at: " + Strings.WEBSITE);
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