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
    private CommandExecutor MainCommand;
    private CommandExecutor HelpCommand;
    private CommandExecutor ReloadCommand;
    private CommandExecutor ListCommand;
    private CommandExecutor CreateCommand;
    private CommandExecutor RemoveCommand;
    private TabCompleter MainTab;
    private TabCompleter HelpTab;
    private TabCompleter ReloadTab;
    private TabCompleter ListTab;
    private TabCompleter CreateTab;
    private TabCompleter RemoveTab;

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
        if (args.length == 0) {
            return MainCommand.onCommand(s, c, label, args);
        } else if (args[0].equalsIgnoreCase("help")) {
            return HelpCommand.onCommand(s, c, label, args);
        } else if (args[0].equalsIgnoreCase("reload")) {
            return ReloadCommand.onCommand(s, c, label, args);
        } else if (args[0].equalsIgnoreCase("list")) {
            return ListCommand.onCommand(s, c, label, args);
        } else if (args[0].equalsIgnoreCase("create")) {
            return CreateCommand.onCommand(s, c, label, args);
        } else if (args[0].equalsIgnoreCase("remove")) {
            return RemoveCommand.onCommand(s, c, label, args);
        } else {
            Utilities.msg(s, Strings.GAMEPREFIX + "&cThat command does not exist.");
        }
        return true;
    }

    public List<String> onTabComplete(CommandSender s, Command c, String label, String[] args) {
        if (args.length == 1) {
            return MainTab.onTabComplete(s, c, label, args);
        } else if (args[0].equalsIgnoreCase("help")) {
            return HelpTab.onTabComplete(s, c, label, args);
        } else if (args[0].equalsIgnoreCase("reload")) {
            return ReloadTab.onTabComplete(s, c, label, args);
        } else if (args[0].equalsIgnoreCase("list")) {
            return ListTab.onTabComplete(s, c, label, args);
        } else if (args[0].equalsIgnoreCase("create")) {
            return CreateTab.onTabComplete(s, c, label, args);
        } else if (args[0].equalsIgnoreCase("remove")) {
            return RemoveTab.onTabComplete(s, c, label, args);
        }
        return null;
    }
}