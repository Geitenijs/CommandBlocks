package com.geitenijs.commandblocks.commands;

import com.geitenijs.commandblocks.Strings;
import com.geitenijs.commandblocks.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Command_Help implements CommandExecutor, TabCompleter {

    public boolean onCommand(final CommandSender s, final Command c, final String label, final String[] args) {
        if (args.length == 1) {
            Utilities.msg(s, Strings.LINE);
            Utilities.msg(s, "&8/&acb help  &7-&f  Shows this list");
            Utilities.msg(s, "&8/&acb reload  &7-&f  Reload the plugin");
            Utilities.msg(s, "&8/&acb list  &7-&f  List all CommandBlocks");
            Utilities.msg(s, "&8/&acb create  &7-&f  Create a CommandBlock");
            Utilities.msg(s, "&8/&acb remove  &7-&f  Remove a CommandBlock");
            Utilities.msg(s, Strings.LINE);
        } else {
            Utilities.msg(s, Strings.HELPUSAGE);
        }
        return true;
    }

    public List<String> onTabComplete(CommandSender s, Command c, String label, String[] args) {
        ArrayList<String> tabs = new ArrayList<>();
        return CommandWrapper.filterTabs(tabs, args);
    }
}