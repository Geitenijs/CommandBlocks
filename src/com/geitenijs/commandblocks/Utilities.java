package com.geitenijs.commandblocks;

import com.geitenijs.commandblocks.commands.CommandWrapper;
import com.geitenijs.commandblocks.updatechecker.UpdateCheck;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

public class Utilities {

    public static FileConfiguration config;
    public static FileConfiguration blocks;
    private static File configFile = new File(Main.plugin.getDataFolder(), "config.yml");
    private static File blocksFile = new File(Main.plugin.getDataFolder(), "blocks.yml");
    private static boolean updateAvailable;
    private static String updateVersion;

    static ConcurrentHashMap<String, Double> timeouts = new ConcurrentHashMap<>();

    static {
        config = YamlConfiguration.loadConfiguration(new File(Main.plugin.getDataFolder(), "config.yml"));
        blocks = YamlConfiguration.loadConfiguration(new File(Main.plugin.getDataFolder(), "blocks.yml"));
    }

    static void startupText() {
        consoleBanner("");
        consoleBanner("&c _______                                  _&8 ______  _             _          ");
        consoleBanner("&c(_______)                                | &8(____  \\| |           | |  &cv" + Strings.VERSION);
        consoleBanner("&c _       ___  ____  ____  _____ ____   __| &8|____)  ) | ___   ____| |  _  ___ ");
        consoleBanner("&c| |     / _ \\|    \\|    \\(____ |  _ \\ / _  &8|  __  (| |/ _ \\ / ___) |_/ )/___)");
        consoleBanner("&c| |____| |_| | | | | | | / ___ | | | ( (_| &8| |__)  ) | |_| ( (___|  _ (|___ |");
        consoleBanner("&c \\______)___/|_|_|_|_|_|_\\_____|_| |_|\\____&8|______/ \\_)___/ \\____)_| \\_|___/ ");
        consoleBanner("");
    }

    static void errorText() {
        consoleBanner("");
        consoleBanner("&c _______ ______  ______ _______ ______  ");
        consoleBanner("&c(_______|_____ \\(_____ (_______|_____ \\ ");
        consoleBanner("&c _____   _____) )_____) )     _ _____) )");
        consoleBanner("&c|  ___) |  __  /|  __  / |   | |  __  / ");
        consoleBanner("&c| |_____| |  \\ \\| |  \\ \\ |___| | |  \\ \\ ");
        consoleBanner("&c|_______)_|   |_|_|   |_\\_____/|_|   |_|");
        consoleBanner("");
    }

    static void createConfigs() {
        config.options().header(Strings.ASCIILOGO
                + "Copyright © " + Strings.COPYRIGHT + " " + Strings.AUTHOR + ", all rights reserved." +
                "\nInformation & Support: " + Strings.WEBSITE
                + "\n\ngeneral:"
                + "\n  colourfulconsole: Console messages will be coloured when this is enabled."
                + "\n  debug: When set to true, the plugin will log more information to the console."
                + "\nupdates:"
                + "\n  check: When enabled, the plugin will check for updates. No automatic downloads, just a subtle notification in the console."
                + "\n  notify: Would you like to get an in-game reminder of a new update? Requires permission 'commandblocks.notify.update'."
                + "\ndefault:"
                + Strings.BLOCKDEFAULTS);
        config.addDefault("general.colourfulconsole", true);
        config.addDefault("general.debug", false);
        config.addDefault("updates.check", true);
        config.addDefault("updates.notify", true);

        List<String> successCommandsConsole = new ArrayList<>();
        List<String> successCommandsPlayer = new ArrayList<>();
        List<String> successMessages = new ArrayList<>();
        List<String> permissionCommandsConsole = new ArrayList<>();
        List<String> permissionCommandsPlayer = new ArrayList<>();
        List<String> permissionMessages = new ArrayList<>();
        List<String> costCommandsConsole = new ArrayList<>();
        List<String> costCommandsPlayer = new ArrayList<>();
        List<String> costMessages = new ArrayList<>();
        List<String> timeoutCommandsConsole = new ArrayList<>();
        List<String> timeoutCommandsPlayer = new ArrayList<>();
        List<String> timeoutMessages = new ArrayList<>();

        successCommandsConsole.add("say {player} used a CommandBlock!");
        successCommandsPlayer.add("me I used a CommandBlock!");
        successMessages.add("&aYou paid €{cost} in order to use this CommandBlock!");
        permissionMessages.add("&cYou don't have permission to do that.");
        costMessages.add("&cYou don't have sufficient funds to do that.");
        timeoutMessages.add("&cPlease wait {time} seconds before doing that again.");

        config.addDefault("default.success.commands.console", successCommandsConsole);
        config.addDefault("default.success.commands.player", successCommandsPlayer);
        config.addDefault("default.success.messages", successMessages);
        config.addDefault("default.permission.value", "commandblocks.use");
        config.addDefault("default.permission.commands.console", permissionCommandsConsole);
        config.addDefault("default.permission.commands.player", permissionCommandsPlayer);
        config.addDefault("default.permission.messages", permissionMessages);
        config.addDefault("default.cost.value", 10D);
        config.addDefault("default.cost.commands.console", costCommandsConsole);
        config.addDefault("default.cost.commands.player", costCommandsPlayer);
        config.addDefault("default.cost.messages", costMessages);
        config.addDefault("default.timeout.value", 5);
        config.addDefault("default.timeout.commands.console", timeoutCommandsConsole);
        config.addDefault("default.timeout.commands.player", timeoutCommandsPlayer);
        config.addDefault("default.timeout.messages", timeoutMessages);
        config.addDefault("default.delay.value", 0);

        blocks.options().header(Strings.ASCIILOGO
                + "Copyright © " + Strings.COPYRIGHT + " " + Strings.AUTHOR + ", all rights reserved." +
                "\nInformation & Support: " + Strings.WEBSITE
                + "\n\nblock:"
                + "\n  location: The location of the CommandBlock. Don't change this unless you know what you're doing!"
                + Strings.BLOCKDEFAULTS);
        config.options().copyHeader(true);
        config.options().copyDefaults(true);
        blocks.options().copyHeader(true);
        blocks.options().copyDefaults(true);
        saveConfigFile();
        reloadConfigFile();
        saveBlocksFile();
        reloadBlocksFile();
    }

    static void registerCommandsAndCompletions() {
        Main.plugin.getCommand("commandblocks").setExecutor(new CommandWrapper());
        Main.plugin.getCommand("cb").setExecutor(new CommandWrapper());
        Main.plugin.getCommand("commandblocks").setTabCompleter(new CommandWrapper());
        Main.plugin.getCommand("cb").setTabCompleter(new CommandWrapper());
    }

    static void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new Events(), Main.plugin);
    }

    static void startSchedulers() {
        BukkitRunnable runnable = (new BukkitRunnable() {
            @Override
            public void run() {
                for (String o : timeouts.keySet()) {
                    timeouts.put(o, timeouts.get(o) - 1);
                    if (timeouts.get(o) <= 0) {
                        timeouts.remove(o);
                    }
                }
            }
        });
        runnable.runTaskTimerAsynchronously(Main.plugin, 0L, 1L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, Utilities::checkForUpdates, 200L, 216000L);
    }

    static void stopSchedulers() {
        Bukkit.getScheduler().cancelTasks(Main.plugin);
    }

    static void startMetrics() {
        Metrics metrics = new Metrics(Main.plugin);
        metrics.addCustomChart(new Metrics.SingleLineChart("definedCommandBlocks", () -> blocks.getKeys(false).size()));
        metrics.addCustomChart(new Metrics.SimplePie("colourfulConsoleEnabled", () -> config.getString("general.colourfulconsole")));
        metrics.addCustomChart(new Metrics.SimplePie("debugEnabled", () -> config.getString("general.debug")));
        metrics.addCustomChart(new Metrics.SimplePie("updateCheckEnabled", () -> config.getString("updates.check")));
        metrics.addCustomChart(new Metrics.SimplePie("updateNotificationEnabled", () -> config.getString("updates.notify")));
    }

    static void done() {
        consoleMsg(Strings.PLUGIN + " v" + Strings.VERSION + " has been enabled");
    }

    private static void checkForUpdates() {
        if (config.getBoolean("updates.check")) {
            UpdateCheck
                    .of(Main.plugin)
                    .resourceId(Strings.RESOURCEID)
                    .handleResponse((versionResponse, version) -> {
                        switch (versionResponse) {
                            case FOUND_NEW:
                                consoleMsg("A new release of " + Strings.PLUGIN + ", v" + version + ", is available! You are still on v" + Strings.VERSION + ".");
                                consoleMsg("To download this update, head over to " + Strings.WEBSITE + "/updates in your browser.");
                                updateVersion = version;
                                updateAvailable = true;
                                break;
                            case LATEST:
                                consoleMsg("You are running the latest version.");
                                updateAvailable = false;
                                break;
                            case UNAVAILABLE:
                                consoleMsg("An error occurred while checking for updates.");
                                updateAvailable = false;
                        }
                    }).check();
        }
    }

    static boolean updateAvailable() {
        return updateAvailable;
    }

    static String updateVersion() {
        return updateVersion;
    }

    public static void reloadConfigFile() {
        if (configFile == null) {
            configFile = new File(Main.plugin.getDataFolder(), "config.yml");
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    static void saveConfigFile() {
        if (config == null || configFile == null) {
            return;
        }
        try {
            config.save(configFile);
        } catch (IOException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Could not save " + configFile, ex);
        }
    }

    public static void reloadBlocksFile() {
        if (blocksFile == null) {
            blocksFile = new File(Main.plugin.getDataFolder(), "blocks.yml");
        }
        blocks = YamlConfiguration.loadConfiguration(blocksFile);
    }

    public static void saveBlocksFile() {
        if (blocks == null || blocksFile == null) {
            return;
        }
        try {
            blocks.save(blocksFile);
        } catch (IOException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Could not save " + blocksFile, ex);
        }
    }

    public static void msg(final CommandSender s, String msg) {
        if (s instanceof Player) {
            msg = ChatColor.translateAlternateColorCodes('&', msg);
        } else {
            msg = ChatColor.translateAlternateColorCodes('&', Strings.INTERNALPREFIX + msg);
            if (!config.getBoolean("general.colourfulconsole")) {
                msg = ChatColor.stripColor(msg);
            }
        }
        s.sendMessage(msg);
    }

    public static void consoleMsg(String msg) {
        msg = ChatColor.translateAlternateColorCodes('&', Strings.INTERNALPREFIX + msg);
        if (!config.getBoolean("general.colourfulconsole")) {
            msg = ChatColor.stripColor(msg);
        }
        Bukkit.getServer().getConsoleSender().sendMessage(msg);
    }

    private static void consoleBanner(final String message) {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
