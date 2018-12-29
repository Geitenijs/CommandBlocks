package com.geitenijs.commandblocks.commands;

import com.geitenijs.commandblocks.Main;
import com.geitenijs.commandblocks.Strings;
import com.geitenijs.commandblocks.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Command_Create implements CommandExecutor, TabCompleter {

    public boolean onCommand(final CommandSender s, final Command c, final String label, final String[] args) {

        if (args[0].equalsIgnoreCase("create")) {
            if (s.hasPermission("commandblocks.create")) {
                if (args.length == 3) {
                    if (args[1].equalsIgnoreCase("current")) {
                        if (s instanceof Player) {
                            final String name = args[2];
                            Player p = (Player) s;
                            Block block;
                            if ((Main.version.contains("v1_13_R2") || Main.version.contains("v1_13_R1"))) {
                                block = p.getTargetBlockExact(5);
                            } else {
                                block = p.getTargetBlock(null, 5);
                            }
                            String blockLocation = block.getLocation().getBlockX() + "#" +
                                    block.getLocation().getBlockY() + "#" +
                                    block.getLocation().getBlockZ() + "#" +
                                    block.getLocation().getWorld().getName();
                            if (Utilities.blocks.getConfigurationSection(name) != null || Utilities.blocks.contains(blockLocation)) {
                                Utilities.msg(s, "&cA CommandBlock with that name already exists.");
                                return false;
                            } else {
                                Utilities.blocks.set(name + ".location", blockLocation);

                                Utilities.blocks.set(name + ".success.commands.console", Utilities.config.getStringList("default.success.commands.console"));
                                Utilities.blocks.set(name + ".success.commands.player", Utilities.config.getStringList("default.success.commands.player"));
                                Utilities.blocks.set(name + ".success.messages", Utilities.config.getStringList("default.success.messages"));

                                Utilities.blocks.set(name + ".permission.value", Utilities.config.getString("default.permission.value"));
                                Utilities.blocks.set(name + ".permission.commands.console", Utilities.config.getStringList("default.permission.commands.console"));
                                Utilities.blocks.set(name + ".permission.commands.player", Utilities.config.getStringList("default.permission.commands.player"));
                                Utilities.blocks.set(name + ".permission.messages", Utilities.config.getStringList("default.permission.messages"));

                                Utilities.blocks.set(name + ".timeout.value", Utilities.config.getInt("default.timeout.value"));
                                Utilities.blocks.set(name + ".timeout.commands.console", Utilities.config.getStringList("default.timeout.commands.console"));
                                Utilities.blocks.set(name + ".timeout.commands.player", Utilities.config.getStringList("default.timeout.commands.player"));
                                Utilities.blocks.set(name + ".timeout.messages", Utilities.config.getStringList("default.timeout.messages"));

                                Utilities.blocks.set(name + ".delay.value", Utilities.config.getInt("default.delay.value"));

                                Utilities.saveBlocksFile();
                                Utilities.reloadBlocksFile();
                                Utilities.msg(s, "&fSuccessfully created CommandBlock &6'" + name + "'&f!");
                            }
                        } else {
                            Utilities.msg(s, Strings.ONLYPLAYER);
                        }
                    } else {
                        Utilities.msg(s, Strings.CREATEUSAGE);
                    }
                } else if (args.length == 7) {
                    if (args[1].equalsIgnoreCase("coords")) {
                        try {
                            final String name = args[2];
                            final int x = Integer.parseInt(args[3]);
                            final int y = Integer.parseInt(args[4]);
                            final int z = Integer.parseInt(args[5]);
                            final String world = args[6];
                            if (Bukkit.getWorld(world) == null) {
                                Utilities.msg(s,
                                        "&cWorld &f'" + world + "'&c doesn't exist, or isn't loaded in memory.");
                            } else {
                                World realWorld = Bukkit.getWorld(world);
                                Location loc = new Location(realWorld, x, y, z);
                                Block block = loc.getBlock();

                                String blockLocation = block.getLocation().getBlockX() + "#" +
                                        block.getLocation().getBlockY() + "#" +
                                        block.getLocation().getBlockZ() + "#" +
                                        block.getLocation().getWorld().getName();
                                if (Utilities.blocks.getConfigurationSection(name) != null || Utilities.blocks.contains(blockLocation)) {
                                    Utilities.msg(s, "&cA CommandBlock with that name already exists.");
                                    return false;
                                } else {
                                    Utilities.blocks.set(name + ".location", blockLocation);

                                    Utilities.blocks.set(name + ".success.commands.console", Utilities.config.getStringList("default.success.commands.console"));
                                    Utilities.blocks.set(name + ".success.commands.player", Utilities.config.getStringList("default.success.commands.player"));
                                    Utilities.blocks.set(name + ".success.messages", Utilities.config.getStringList("default.success.messages"));

                                    Utilities.blocks.set(name + ".permission.value", Utilities.config.getString("default.permission.value"));
                                    Utilities.blocks.set(name + ".permission.commands.console", Utilities.config.getStringList("default.permission.commands.console"));
                                    Utilities.blocks.set(name + ".permission.commands.player", Utilities.config.getStringList("default.permission.commands.player"));
                                    Utilities.blocks.set(name + ".permission.messages", Utilities.config.getStringList("default.permission.messages"));

                                    Utilities.blocks.set(name + ".timeout.value", Utilities.config.getInt("default.timeout.value"));
                                    Utilities.blocks.set(name + ".timeout.commands.console", Utilities.config.getStringList("default.timeout.commands.console"));
                                    Utilities.blocks.set(name + ".timeout.commands.player", Utilities.config.getStringList("default.timeout.commands.player"));
                                    Utilities.blocks.set(name + ".timeout.messages", Utilities.config.getStringList("default.timeout.messages"));

                                    Utilities.blocks.set(name + ".delay.value", Utilities.config.getInt("default.delay.value"));

                                    Utilities.saveBlocksFile();
                                    Utilities.reloadBlocksFile();
                                    Utilities.msg(s, "&fSuccessfully created CommandBlock &6'" + name + "'&f!");
                                }
                            }
                        } catch (NumberFormatException ex) {
                            Utilities.msg(s, Strings.UNUSABLE);
                        }
                    } else {
                        Utilities.msg(s, Strings.CREATEUSAGE);
                    }

                } else {
                    Utilities.msg(s, Strings.CREATEUSAGE);
                }
            } else {
                Utilities.msg(s, Strings.NOPERM);
            }
        }
        return true;
    }

    public List<String> onTabComplete(CommandSender s, Command c, String label, String[] args) {
        ArrayList<String> tabs = new ArrayList<>();
        String[] newArgs = CommandWrapper.getArgs(args);
        if (args[0].equals("create")) {
            if (s.hasPermission("commandblocks.create")) {
                if (newArgs.length == 1) {
                    tabs.add("current");
                    tabs.add("coords");
                }
                if (args[1].equals("coords")) {
                    if (s instanceof Player) {
                        Player player = (Player) s;
                        Location loc = player.getLocation();
                        String locSerialized = loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
                        String[] locString = locSerialized.split(",");
                        if (newArgs.length == 2) {
                            tabs.add("<name>");
                        }
                        if (newArgs.length == 3) {
                            tabs.add(locString[1]);
                        }
                        if (newArgs.length == 4) {
                            tabs.add(locString[2]);
                        }
                        if (newArgs.length == 5) {
                            tabs.add(locString[3]);
                        }
                        if (newArgs.length == 6) {
                            tabs.add(locString[0]);
                        }
                        if (newArgs.length > 6) {
                            tabs.clear();
                        }
                    } else {
                        if (newArgs.length == 2) {
                            tabs.add("<name>");
                        }
                        if (newArgs.length == 3) {
                            tabs.add("<0>");
                        }
                        if (newArgs.length == 4) {
                            tabs.add("<0>");
                        }
                        if (newArgs.length == 5) {
                            tabs.add("<0>");
                        }
                        if (newArgs.length == 6) {
                            tabs.add("<world>");
                        }
                        if (newArgs.length > 6) {
                            tabs.clear();
                        }
                    }
                }
                if (args[1].equals("current")) {
                    if (newArgs.length == 2) {
                        tabs.add("<name>");
                    }
                }
            }
            return CommandWrapper.filterTabs(tabs, args);
        }
        return null;
    }
}