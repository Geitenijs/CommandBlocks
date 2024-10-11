package com.geitenijs.commandblocks;

import java.util.Arrays;
import java.util.List;

public class Strings {

    public static final String PLUGINCOLOURED = "&c&lCommand&8&lBlocks";
    public static final String IGFULLPREFIX = PLUGINCOLOURED + " &7&l» ";
    public static final String IGPREFIX = "&c&lC&8&lB &7&l» ";
    public static final String VERSION = "1.4.0";
    public static final String AUTHOR = "Geitenijs";
    public static final String NOPERM = Strings.IGPREFIX + "&cYou don't have permission to do that.";
    public static final String UNUSABLE = Strings.IGPREFIX + "&cOne or more values you've entered are unusable.";
    public static final String ONLYPLAYER = Strings.IGPREFIX + "&cYou can only do that as an in-game player.";
    public static final String NOVAULT = Strings.IGPREFIX + "&cPlease install Vault 1.7.0+ first.";
    public static final String LINE = "&8&m----------&r " + PLUGINCOLOURED + " &8&m----------";
    public static final String HELPUSAGE = "&fUsage: &c/cb help";
    public static final String RELOADUSAGE = "&fUsage: &c/cb reload";
    public static final String LISTUSAGE = "&fUsage: &c/cb list";
    public static final String CREATEUSAGE = "&fUsage: &c/cb create &f(&ccoords <name> <x> <y> <z> <world> [base] &f|&c current <name> [base]&f)";
    public static final String REMOVEUSAGE = "&fUsage: &c/cb remove <name>";
    static final String PLUGIN = "CommandBlocks";
    static final String INTERNALPREFIX = "[CommandBlocks] ";
    static final int RESOURCEID = 62720;
    static final String WEBSITE = "https://www.spigotmc.org/resources/" + Strings.RESOURCEID;
    static final List<String> ASCIILOGO = Arrays.asList(
            " _______                                  _ ______  _             _          ",
            "(_______)                                | (____  \\| |           | |  v" + Strings.VERSION,
            " _       ___  ____  ____  _____ ____   __| |____)  ) | ___   ____| |  _  ___ ",
            "| |     / _ \\|    \\|    \\(____ |  _ \\ / _  |  __  (| |/ _ \\ / ___) |_/ )/___)",
            "| |____| |_| | | | | | | / ___ | | | ( (_| | |__)  ) | |_| ( (___|  _ (|___ |",
            " \\______)___/|_|_|_|_|_|_\\_____|_| |_|\\____|______/ \\_)___/ \\____)_| \\_|___/ ",
            ""
    );
    static final List<String> BLOCKDEFAULTS = Arrays.asList(
            "  success:",
            "    commands:",
            "      console:",
            "      - A list of commands to be executed by the console. Variables: {player}, {cost}.",
            "      player:",
            "      - A list of commands to be executed by the player. Variables: {player}, {cost}.",
            "    messages:",
            "    - A list of messages to be displayed to the player. Variables: {player}, {cost}.",
            "  permission:",
            "    value: The required permission to use this CommandBlock. Remove to disable.",
            "    commands:",
            "      console:",
            "      - A list of commands to be executed by the console. Variables: {player}, {permission}.",
            "      player:",
            "      - A list of commands to be executed by the player. Variables: {player}, {permission}.",
            "    messages:",
            "    - A list of messages to be displayed to the player. Variables: {player}, {permission}.",
            "  cost:",
            "    value: The required cost to use the CommandBlock. Set to '0' for no cost.",
            "    commands:",
            "      console:",
            "      - A list of commands to be executed by the console. Variables: {player}, {cost}.",
            "      player:",
            "      - A list of commands to be executed by the player. Variables: {player}, {cost}.",
            "    messages:",
            "    - A list of messages to be displayed to the player. Variables: {player}, {cost}.",
            "  timeout:",
            "    value: A timeout for the CommandBlock. Set to '0' to disable.",
            "    bypasspermission: The required permission to bypass the timeout for this CommandBlock. Remove to disable.",
            "    commands:",
            "      console:",
            "      - A list of commands to be executed by the console. Variables: {player}, {time}.",
            "      player:",
            "      - A list of commands to be executed by the player. Variables: {player}, {time}.",
            "    messages:",
            "    - A list of messages to be displayed to the player. Variables: {player}, {time}.",
            "  delay:",
            "    value: A delay for the CommandBlock. Set to '0' to disable."
    );
    static final String NOSTAT = "None";
    static final String DEPENDENCIES_VAULT_INCOMPATIBLE = "The required version of Vault for " + Strings.PLUGIN + " v" + Strings.VERSION + " is 1.7.0 or newer.";
}
