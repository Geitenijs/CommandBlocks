package com.geitenijs.commandblocks.commands;

import com.geitenijs.commandblocks.Strings;
import com.geitenijs.commandblocks.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Command_Main implements CommandExecutor, TabCompleter {

    public boolean onCommand(final CommandSender s, final Command c, final String label, final String[] args) {
        Utilities.msg(s, Strings.GAMEPREFIX + "&fRunning &9v" + Strings.VERSION);
        Utilities.msg(s, Strings.GAMEPREFIX + "&fMade by &6" + Strings.AUTHOR + "&f, © " + Strings.COPYRIGHT);
        return true;
    }

    public List<String> onTabComplete(CommandSender s, Command c, String label, String[] args) {
        ArrayList<String> tabs = new ArrayList<>();
        tabs.add("help");
        tabs.add("reload");
        tabs.add("list");
        tabs.add("create");
        tabs.add("remove");
        return CommandWrapper.filterTabs(tabs, args);
    }
}