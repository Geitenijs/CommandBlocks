package com.geitenijs.commandblocks.commands;

import com.geitenijs.commandblocks.Strings;
import com.geitenijs.commandblocks.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Command_Main implements CommandExecutor, TabCompleter {

    public boolean onCommand(final CommandSender s, final Command c, final String label, final String[] args) {

        if (args.length == 0) {
            if (Bukkit.getVersion().contains("Spigot")) {
                Utilities.msg(s, Strings.GAMEPREFIX + "&fRunning &9v" + Strings.VERSION + "&f on &cSpigot");
            } else if (Bukkit.getVersion().contains("Paper")) {
                Utilities.msg(s, Strings.GAMEPREFIX + "&fRunning &9v" + Strings.VERSION + "&f on &cPaper");
            } else if (Bukkit.getVersion().contains("Bukkit")) {
                Utilities.msg(s, Strings.GAMEPREFIX + "&fRunning &9v" + Strings.VERSION + "&f on &cBukkit");
            } else {
                Utilities.msg(s, Strings.GAMEPREFIX + "&fRunning &9v" + Strings.VERSION);
            }
            Utilities.msg(s, Strings.GAMEPREFIX + "&fMade by &6" + Strings.AUTHOR + "&f, © " + Strings.COPYRIGHT);
        }
        return true;
    }

    public List<String> onTabComplete(CommandSender s, Command c, String label, String[] args) {
        ArrayList<String> tabs = new ArrayList<>();
        String[] newArgs = CommandWrapper.getArgs(args);
        if (newArgs.length == 0) {
            if (s.hasPermission("commandblocks.help") || s.hasPermission("commandblocks.reload") || s.hasPermission("commandblocks.list")
                    || s.hasPermission("commandblocks.create") || s.hasPermission("commandblocks.remove")) {
                tabs.add("help");
                tabs.add("reload");
                tabs.add("list");
                tabs.add("create");
                tabs.add("remove");
                return CommandWrapper.filterTabs(tabs, args);
            }
        }
        return null;
    }
}