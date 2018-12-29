package com.geitenijs.commandblocks.commands;

import com.geitenijs.commandblocks.Strings;
import com.geitenijs.commandblocks.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Command_Reload implements CommandExecutor, TabCompleter {

    public boolean onCommand(final CommandSender s, final Command c, final String label, final String[] args) {

        if (args[0].equalsIgnoreCase("reload")) {
            if (s.hasPermission("commandblocks.reload")) {
                if (args.length == 1) {
                    Utilities.reloadConfigFile();
                    Utilities.reloadBlocksFile();
                    Utilities.msg(s, "&aConfiguration reloaded successfully.");
                } else {
                    Utilities.msg(s, Strings.RELOADUSAGE);
                }
            } else {
                Utilities.msg(s, Strings.NOPERM);
            }
        }
        return true;
    }

    public List<String> onTabComplete(CommandSender s, Command c, String label, String[] args) {
        ArrayList<String> tabs = new ArrayList<>();
        if (args[0].equals("reload")) {
            if (s.hasPermission("commandblocks.reload")) {
                tabs.clear();
            }
            return CommandWrapper.filterTabs(tabs, args);
        }
        return null;
    }
}