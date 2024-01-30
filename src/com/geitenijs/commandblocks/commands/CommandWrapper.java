package com.geitenijs.commandblocks.commands;

import com.geitenijs.commandblocks.Strings;
import com.geitenijs.commandblocks.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommandWrapper implements CommandExecutor, TabCompleter {
    private final CommandExecutor MainCommand;
    private final CommandExecutor HelpCommand;
    private final CommandExecutor ReloadCommand;
    private final CommandExecutor ListCommand;
    private final CommandExecutor CreateCommand;
    private final CommandExecutor RemoveCommand;
    private final TabCompleter MainTab;
    private final TabCompleter HelpTab;
    private final TabCompleter ReloadTab;
    private final TabCompleter ListTab;
    private final TabCompleter CreateTab;
    private final TabCompleter RemoveTab;

    public CommandWrapper() {
        MainCommand = new Command_Main();
        HelpCommand = new Command_Help();
        ReloadCommand = new Command_Reload();
        ListCommand = new Command_List();
        CreateCommand = new Command_Create();
        RemoveCommand = new Command_Remove();
        MainTab = new Command_Main();
        HelpTab = new Command_Help();
        ReloadTab = new Command_Reload();
        ListTab = new Command_List();
        CreateTab = new Command_Create();
        RemoveTab = new Command_Remove();
    }

    static ArrayList<String> filterTabs(ArrayList<String> list, String[] origArgs) {
        if (origArgs.length == 0)
            return list;
        Iterator<String> itel = list.iterator();
        String label = origArgs[origArgs.length - 1].toLowerCase();
        while (itel.hasNext()) {
            String name = itel.next();
            if (name.toLowerCase().startsWith(label))
                continue;
            itel.remove();
        }
        return list;
    }

    static String[] getArgs(String[] args) {
        ArrayList<String> newArgs = new ArrayList<>();
        for (int i = 0; i < args.length - 1; i++) {
            String s = args[i];
            if (s.trim().isEmpty())
                continue;
            newArgs.add(s);
        }
        return newArgs.toArray(new String[0]);
    }

    public boolean onCommand(final CommandSender s, final Command c, final String label, final String[] args) {
        if (c.getName().equalsIgnoreCase("commandblocks") || c.getName().equalsIgnoreCase("cb")) {
            if (args.length == 0) {
                return MainCommand.onCommand(s, c, label, args);
            } else if (args[0].equalsIgnoreCase("help")) {
                if (s.hasPermission("commandblocks.help")) {
                    return HelpCommand.onCommand(s, c, label, args);
                } else {
                    Utilities.msg(s, Strings.NOPERM);
                }
            } else if (args[0].equalsIgnoreCase("reload")) {
                if (s.hasPermission("commandblocks.reload")) {
                    return ReloadCommand.onCommand(s, c, label, args);
                } else {
                    Utilities.msg(s, Strings.NOPERM);
                }
            } else if (args[0].equalsIgnoreCase("list")) {
                if (s.hasPermission("commandblocks.list")) {
                    return ListCommand.onCommand(s, c, label, args);
                } else {
                    Utilities.msg(s, Strings.NOPERM);
                }
            } else if (args[0].equalsIgnoreCase("create")) {
                if (s.hasPermission("commandblocks.create")) {
                    return CreateCommand.onCommand(s, c, label, args);
                } else {
                    Utilities.msg(s, Strings.NOPERM);
                }
            } else if (args[0].equalsIgnoreCase("remove")) {
                if (s.hasPermission("commandblocks.remove")) {
                    return RemoveCommand.onCommand(s, c, label, args);
                } else {
                    Utilities.msg(s, Strings.NOPERM);
                }
            } else {
                Utilities.msg(s, Strings.IGPREFIX + "&cThat command does not exist.");
            }
        }
        return true;
    }

    public List<String> onTabComplete(CommandSender s, Command c, String label, String[] args) {
        if (c.getName().equalsIgnoreCase("commandblocks") || c.getName().equalsIgnoreCase("cb")) {
            if (args.length == 1) {
                if (s.hasPermission("commandblocks.help")) {
                    return MainTab.onTabComplete(s, c, label, args);
                }
            } else if (args[0].equalsIgnoreCase("help")) {
                if (s.hasPermission("commandblocks.help")) {
                    return HelpTab.onTabComplete(s, c, label, args);
                }
            } else if (args[0].equalsIgnoreCase("reload")) {
                if (s.hasPermission("commandblocks.reload")) {
                    return ReloadTab.onTabComplete(s, c, label, args);
                }
            } else if (args[0].equalsIgnoreCase("list")) {
                if (s.hasPermission("commandblocks.list")) {
                    return ListTab.onTabComplete(s, c, label, args);
                }
            } else if (args[0].equalsIgnoreCase("create")) {
                if (s.hasPermission("commandblocks.create")) {
                    return CreateTab.onTabComplete(s, c, label, args);
                }
            } else if (args[0].equalsIgnoreCase("remove")) {
                if (s.hasPermission("commandblocks.remove")) {
                    return RemoveTab.onTabComplete(s, c, label, args);
                }
            }
        }
        return null;
    }
}