package com.geitenijs.commandblocks.commands;

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
        if (args.length == 3 || args.length == 4) {
            if (args[1].equalsIgnoreCase("current")) {
                if (s instanceof Player p) {
                    final String name = args[2];
                    Block block;
                    block = p.getTargetBlock(null, 5);
                    String blockLocation = block.getLocation().getBlockX() + "#" +
                            block.getLocation().getBlockY() + "#" +
                            block.getLocation().getBlockZ() + "#" +
                            block.getLocation().getWorld().getName();
                    if (Utilities.blocks.getConfigurationSection(name) != null || Utilities.blocks.contains(blockLocation)) {
                        Utilities.msg(s, Strings.IGPREFIX + "&cA CommandBlock named &f'" + name + "'&c already exists.");
                        return false;
                    }
                    if (args.length == 3) {
                        Utilities.blocks.set(name + ".location", blockLocation);

                        Utilities.blocks.set(name + ".success.commands.console", Utilities.config.getStringList("default.success.commands.console"));
                        Utilities.blocks.set(name + ".success.commands.player", Utilities.config.getStringList("default.success.commands.player"));
                        Utilities.blocks.set(name + ".success.messages", Utilities.config.getStringList("default.success.messages"));

                        Utilities.blocks.set(name + ".permission.value", Utilities.config.getString("default.permission.value"));
                        Utilities.blocks.set(name + ".permission.commands.console", Utilities.config.getStringList("default.permission.commands.console"));
                        Utilities.blocks.set(name + ".permission.commands.player", Utilities.config.getStringList("default.permission.commands.player"));
                        Utilities.blocks.set(name + ".permission.messages", Utilities.config.getStringList("default.permission.messages"));

                        Utilities.blocks.set(name + ".cost.value", Utilities.config.getDouble("default.cost.value"));
                        Utilities.blocks.set(name + ".cost.bypasspermission", Utilities.config.getString("default.cost.bypasspermission"));
                        Utilities.blocks.set(name + ".cost.commands.console", Utilities.config.getStringList("default.cost.commands.console"));
                        Utilities.blocks.set(name + ".cost.commands.player", Utilities.config.getStringList("default.cost.commands.player"));
                        Utilities.blocks.set(name + ".cost.messages", Utilities.config.getStringList("default.cost.messages"));

                        Utilities.blocks.set(name + ".timeout.value", Utilities.config.getInt("default.timeout.value"));
                        Utilities.blocks.set(name + ".timeout.bypasspermission", Utilities.config.getString("default.timeout.bypasspermission"));
                        Utilities.blocks.set(name + ".timeout.commands.console", Utilities.config.getStringList("default.timeout.commands.console"));
                        Utilities.blocks.set(name + ".timeout.commands.player", Utilities.config.getStringList("default.timeout.commands.player"));
                        Utilities.blocks.set(name + ".timeout.messages", Utilities.config.getStringList("default.timeout.messages"));

                        Utilities.blocks.set(name + ".delay.value", Utilities.config.getInt("default.delay.value"));

                        Utilities.saveBlocksFile();
                        Utilities.reloadBlocksFile();
                        Utilities.msg(s, Strings.IGPREFIX + "&fSuccessfully created CommandBlock &6'" + name + "'&f!");
                    } else {
                        final String base = args[3];
                        if (Utilities.blocks.getConfigurationSection(base) != null) {
                            Utilities.blocks.set(name + ".location", blockLocation);

                            Utilities.blocks.set(name + ".success.commands.console", Utilities.blocks.getStringList(base + ".success.commands.console"));
                            Utilities.blocks.set(name + ".success.commands.player", Utilities.blocks.getStringList(base + ".success.commands.player"));
                            Utilities.blocks.set(name + ".success.messages", Utilities.blocks.getStringList(base + ".success.messages"));

                            Utilities.blocks.set(name + ".permission.value", Utilities.blocks.getString(base + ".permission.value"));
                            Utilities.blocks.set(name + ".permission.commands.console", Utilities.blocks.getStringList(base + ".permission.commands.console"));
                            Utilities.blocks.set(name + ".permission.commands.player", Utilities.blocks.getStringList(base + ".permission.commands.player"));
                            Utilities.blocks.set(name + ".permission.messages", Utilities.blocks.getStringList(base + ".permission.messages"));

                            Utilities.blocks.set(name + ".cost.value", Utilities.blocks.getDouble(base + ".cost.value"));
                            Utilities.blocks.set(name + ".cost.bypasspermission", Utilities.blocks.getString(base + ".cost.bypasspermission"));
                            Utilities.blocks.set(name + ".cost.commands.console", Utilities.blocks.getStringList(base + ".cost.commands.console"));
                            Utilities.blocks.set(name + ".cost.commands.player", Utilities.blocks.getStringList(base + ".cost.commands.player"));
                            Utilities.blocks.set(name + ".cost.messages", Utilities.blocks.getStringList(base + ".cost.messages"));

                            Utilities.blocks.set(name + ".timeout.value", Utilities.blocks.getInt(base + ".timeout.value"));
                            Utilities.blocks.set(name + ".timeout.bypasspermission", Utilities.blocks.getString(base + ".timeout.bypasspermission"));
                            Utilities.blocks.set(name + ".timeout.commands.console", Utilities.blocks.getStringList(base + ".timeout.commands.console"));
                            Utilities.blocks.set(name + ".timeout.commands.player", Utilities.blocks.getStringList(base + ".timeout.commands.player"));
                            Utilities.blocks.set(name + ".timeout.messages", Utilities.blocks.getStringList(base + ".timeout.messages"));

                            Utilities.blocks.set(name + ".delay.value", Utilities.blocks.getInt(base + ".delay.value"));

                            Utilities.saveBlocksFile();
                            Utilities.reloadBlocksFile();
                            Utilities.msg(s, Strings.IGPREFIX + "&fSuccessfully created CommandBlock &6'" + name + "'&f!");
                        } else {
                            Utilities.msg(s, Strings.IGPREFIX + "&cFailed to copy. CommandBlock &f'" + base + "'&c does not exist.");
                        }
                    }
                } else {
                    Utilities.msg(s, Strings.ONLYPLAYER);
                }
            } else {
                Utilities.msg(s, Strings.CREATEUSAGE);
            }
        } else if (args.length == 7 || args.length == 8) {
            if (args[1].equalsIgnoreCase("coords")) {
                try {
                    final String name = args[2];
                    final int x = Integer.parseInt(args[3]);
                    final int y = Integer.parseInt(args[4]);
                    final int z = Integer.parseInt(args[5]);
                    final String world = args[6];
                    if (Bukkit.getWorld(world) == null) {
                        Utilities.msg(s, Strings.IGPREFIX + "&cWorld &f'" + world + "'&c doesn't exist, or isn't loaded in memory.");
                        return false;
                    }
                    World realWorld = Bukkit.getWorld(world);
                    Location loc = new Location(realWorld, x, y, z);
                    Block block = loc.getBlock();

                    String blockLocation = block.getLocation().getBlockX() + "#" +
                            block.getLocation().getBlockY() + "#" +
                            block.getLocation().getBlockZ() + "#" +
                            block.getLocation().getWorld().getName();
                    if (Utilities.blocks.getConfigurationSection(name) != null || Utilities.blocks.contains(blockLocation)) {
                        Utilities.msg(s, Strings.IGPREFIX + "&cA CommandBlock named &f'" + name + "'&c already exists.");
                        return false;
                    }
                    if (args.length == 7) {
                        Utilities.blocks.set(name + ".location", blockLocation);

                        Utilities.blocks.set(name + ".success.commands.console", Utilities.config.getStringList("default.success.commands.console"));
                        Utilities.blocks.set(name + ".success.commands.player", Utilities.config.getStringList("default.success.commands.player"));
                        Utilities.blocks.set(name + ".success.messages", Utilities.config.getStringList("default.success.messages"));

                        Utilities.blocks.set(name + ".permission.value", Utilities.config.getString("default.permission.value"));
                        Utilities.blocks.set(name + ".permission.commands.console", Utilities.config.getStringList("default.permission.commands.console"));
                        Utilities.blocks.set(name + ".permission.commands.player", Utilities.config.getStringList("default.permission.commands.player"));
                        Utilities.blocks.set(name + ".permission.messages", Utilities.config.getStringList("default.permission.messages"));

                        Utilities.blocks.set(name + ".cost.value", Utilities.config.getDouble("default.cost.value"));
                        Utilities.blocks.set(name + ".cost.bypasspermission", Utilities.config.getString("default.cost.bypasspermission"));
                        Utilities.blocks.set(name + ".cost.commands.console", Utilities.config.getStringList("default.cost.commands.console"));
                        Utilities.blocks.set(name + ".cost.commands.player", Utilities.config.getStringList("default.cost.commands.player"));
                        Utilities.blocks.set(name + ".cost.messages", Utilities.config.getStringList("default.cost.messages"));

                        Utilities.blocks.set(name + ".timeout.value", Utilities.config.getInt("default.timeout.value"));
                        Utilities.blocks.set(name + ".timeout.bypasspermission", Utilities.config.getString("default.timeout.bypasspermission"));
                        Utilities.blocks.set(name + ".timeout.commands.console", Utilities.config.getStringList("default.timeout.commands.console"));
                        Utilities.blocks.set(name + ".timeout.commands.player", Utilities.config.getStringList("default.timeout.commands.player"));
                        Utilities.blocks.set(name + ".timeout.messages", Utilities.config.getStringList("default.timeout.messages"));

                        Utilities.blocks.set(name + ".delay.value", Utilities.config.getInt("default.delay.value"));

                        Utilities.saveBlocksFile();
                        Utilities.reloadBlocksFile();
                        Utilities.msg(s, Strings.IGPREFIX + "&fSuccessfully created CommandBlock &6'" + name + "'&f!");
                    } else {
                        final String base = args[7];
                        if (Utilities.blocks.getConfigurationSection(base) != null) {
                            Utilities.blocks.set(name + ".location", blockLocation);

                            Utilities.blocks.set(name + ".success.commands.console", Utilities.blocks.getStringList(base + ".success.commands.console"));
                            Utilities.blocks.set(name + ".success.commands.player", Utilities.blocks.getStringList(base + ".success.commands.player"));
                            Utilities.blocks.set(name + ".success.messages", Utilities.blocks.getStringList(base + ".success.messages"));

                            Utilities.blocks.set(name + ".permission.value", Utilities.blocks.getString(base + ".permission.value"));
                            Utilities.blocks.set(name + ".permission.commands.console", Utilities.blocks.getStringList(base + ".permission.commands.console"));
                            Utilities.blocks.set(name + ".permission.commands.player", Utilities.blocks.getStringList(base + ".permission.commands.player"));
                            Utilities.blocks.set(name + ".permission.messages", Utilities.blocks.getStringList(base + ".permission.messages"));

                            Utilities.blocks.set(name + ".cost.value", Utilities.blocks.getDouble(base + ".cost.value"));
                            Utilities.blocks.set(name + ".cost.bypasspermission", Utilities.blocks.getString(base + ".cost.bypasspermission"));
                            Utilities.blocks.set(name + ".cost.commands.console", Utilities.blocks.getStringList(base + ".cost.commands.console"));
                            Utilities.blocks.set(name + ".cost.commands.player", Utilities.blocks.getStringList(base + ".cost.commands.player"));
                            Utilities.blocks.set(name + ".cost.messages", Utilities.blocks.getStringList(base + ".cost.messages"));

                            Utilities.blocks.set(name + ".timeout.value", Utilities.blocks.getInt(base + ".timeout.value"));
                            Utilities.blocks.set(name + ".timeout.bypasspermission", Utilities.blocks.getString(base + ".timeout.bypasspermission"));
                            Utilities.blocks.set(name + ".timeout.commands.console", Utilities.blocks.getStringList(base + ".timeout.commands.console"));
                            Utilities.blocks.set(name + ".timeout.commands.player", Utilities.blocks.getStringList(base + ".timeout.commands.player"));
                            Utilities.blocks.set(name + ".timeout.messages", Utilities.blocks.getStringList(base + ".timeout.messages"));

                            Utilities.blocks.set(name + ".delay.value", Utilities.blocks.getInt(base + ".delay.value"));

                            Utilities.saveBlocksFile();
                            Utilities.reloadBlocksFile();
                            Utilities.msg(s, Strings.IGPREFIX + "&fSuccessfully created CommandBlock &6'" + name + "'&f!");
                        } else {
                            Utilities.msg(s, Strings.IGPREFIX + "&cFailed to copy. CommandBlock &f'" + base + "'&c does not exist.");
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
        return true;
    }

    public List<String> onTabComplete(CommandSender s, Command c, String label, String[] args) {
        ArrayList<String> tabs = new ArrayList<>();
        String[] newArgs = CommandWrapper.getArgs(args);
        if (newArgs.length == 1) {
            tabs.add("current");
            tabs.add("coords");
        }
        if (args[1].equals("coords")) {
            if (s instanceof Player player) {
                Location loc = player.getLocation();
                if (newArgs.length == 2) {
                    tabs.add("<name>");
                }
                if (newArgs.length == 3) {
                    tabs.add(String.valueOf(loc.getBlockX()));
                }
                if (newArgs.length == 4) {
                    tabs.add(String.valueOf(loc.getBlockY()));
                }
                if (newArgs.length == 5) {
                    tabs.add(String.valueOf(loc.getBlockZ()));
                }
                if (newArgs.length == 6) {
                    tabs.add(loc.getWorld().getName());
                }
                if (newArgs.length == 7) {
                    tabs.addAll(Utilities.blocks.getKeys(false));
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
                if (newArgs.length == 7) {
                    tabs.addAll(Utilities.blocks.getKeys(false));
                }
            }
        }
        if (args[1].equals("current")) {
            if (newArgs.length == 2) {
                tabs.add("<name>");
            }
            if (newArgs.length == 3) {
                tabs.addAll(Utilities.blocks.getKeys(false));
            }
        }
        return CommandWrapper.filterTabs(tabs, args);
    }
}