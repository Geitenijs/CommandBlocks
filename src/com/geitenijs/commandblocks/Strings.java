package com.geitenijs.commandblocks;

public class Strings {

    static final String PLUGIN = "CommandBlocks";
    static final String INTERNALPREFIX = "[CommandBlocks] ";
    public static final String DEBUGPREFIX = "[DEBUG] ";
    public static final String GAMEPREFIX = "&cCommand&8Blocks &7// ";
    public static final String VERSION = "1.2.2";
    public static final String AUTHOR = "Geitenijs";
    public static final String COPYRIGHT = "2018-2020";
    static final int RESOURCEID = 62720;
    static final String WEBSITE = "https://www.spigotmc.org/resources/" + Strings.RESOURCEID;
    static final String ASCIILOGO = "" +
            "\n _______                                  _ ______  _             _          " +
            "\n(_______)                                | (____  \\| |           | |  v" + Strings.VERSION +
            "\n _       ___  ____  ____  _____ ____   __| |____)  ) | ___   ____| |  _  ___ " +
            "\n| |     / _ \\|    \\|    \\(____ |  _ \\ / _  |  __  (| |/ _ \\ / ___) |_/ )/___)" +
            "\n| |____| |_| | | | | | | / ___ | | | ( (_| | |__)  ) | |_| ( (___|  _ (|___ |" +
            "\n \\______)___/|_|_|_|_|_|_\\_____|_| |_|\\____|______/ \\_)___/ \\____)_| \\_|___/ " +
            "\n\n";
    static final String BLOCKDEFAULTS = "\n  success:"
            + "\n    commands:"
            + "\n      console:"
            + "\n      - A list of commands to be executed by the console. Variables: {player}, {cost}. Disable by setting 'console:' to '[]' and removing all entries."
            + "\n      player:"
            + "\n      - A list of commands to be executed by the player. Variables: {player}, {cost}. Disable by setting 'player:' to '[]' and removing all entries."
            + "\n    messages:"
            + "\n    - A list of messages to be displayed to the player. Variables: {player}, {cost}. Disable by setting 'messages:' to '[]' and removing all entries."
            + "\n  permission"
            + "\n    value: The required permission to use the CommandBlock. Can NOT be disabled."
            + "\n    commands:"
            + "\n      console:"
            + "\n      - A list of commands to be executed by the console. Variables: {player}, {permission}. Disable by setting 'console:' to '[]' and removing all entries."
            + "\n      player:"
            + "\n      - A list of commands to be executed by the player. Variables: {player}, {permission}. Disable by setting 'player:' to '[]' and removing all entries."
            + "\n    messages:"
            + "\n    - A list of messages to be displayed to the player. Variables: {player}, {permission}. Disable by setting 'messages:' to '[]' and removing all entries."
            + "\n  cost"
            + "\n    value: The required cost to use the CommandBlock. Set to '0' for no cost."
            + "\n    commands:"
            + "\n      console:"
            + "\n      - A list of commands to be executed by the console. Variables: {player}, {cost}. Disable by setting 'console:' to '[]' and removing all entries."
            + "\n      player:"
            + "\n      - A list of commands to be executed by the player. Variables: {player}, {cost}. Disable by setting 'player:' to '[]' and removing all entries."
            + "\n    messages:"
            + "\n    - A list of messages to be displayed to the player. Variables: {player}, {cost}. Disable by setting 'messages:' to '[]' and removing all entries."
            + "\n  timeout"
            + "\n    value: A timeout for the CommandBlock. Set to '0' to disable."
            + "\n    commands:"
            + "\n      console:"
            + "\n      - A list of commands to be executed by the console. Variables: {player}, {time}. Disable by setting 'console:' to '[]' and removing all entries."
            + "\n      player:"
            + "\n      - A list of commands to be executed by the player. Variables: {player}, {time}. Disable by setting 'player:' to '[]' and removing all entries."
            + "\n    messages:"
            + "\n    - A list of messages to be displayed to the player. Variables: {player}, {time}. Disable by setting 'messages:' to '[]' and removing all entries."
            + "\n  delay"
            + "\n    value: A delay for the CommandBlock. Set to '0' to disable.";

    public static final String NOPERM = Strings.GAMEPREFIX + "&cYou don't have permission to do that.";
    public static final String UNUSABLE = "&cOne or more values you've entered are unusable.";
    public static final String ONLYPLAYER = "&cYou can only do that as an in-game player.";
    public static final String NOVAULT = "&cThis requires you to have Vault installed.";
    public static final String UPDATEVAULT = "&cThis requires you to have Vault version 1.7.0 or newer installed.";

    static final String DEPENDENCIES_VAULT_COMPATIBLE = "Found a compatible version of Vault! (1.7.0+)";
    static final String DEPENDENCIES_VAULT_INCOMPATIBLE = "The required version of Vault for " + Strings.PLUGIN + " v" + Strings.VERSION + " is 1.7.0 or newer.";
    static final String DEPENDENCIES_VAULT_MISSING = "Vault plugin not found. " + Strings.PLUGIN + " will have reduced functionality.";

    public static final String HELPUSAGE = "&fUsage: &c/cb help";
    public static final String RELOADUSAGE = "&fUsage: &c/cb reload";
    public static final String LISTUSAGE = "&fUsage: &c/cb list";
    public static final String CREATEUSAGE = "&fUsage: &c/cb create &f(&ccoords <name> <x> <y> <z> <world> &f|&c current <name>&f)";
    public static final String REMOVEUSAGE = "&fUsage: &c/cb remove <name>";
}
