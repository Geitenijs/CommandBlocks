package com.geitenijs.commandblocks.commands;

import com.geitenijs.commandblocks.Strings;
import com.geitenijs.commandblocks.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Command_List implements CommandExecutor, TabCompleter {

    public boolean onCommand(final CommandSender s, final Command c, final String label, final String[] args) {
        if (args.length == 1) {
            if (Utilities.blocks.getKeys(false).isEmpty()) {
                Utilities.msg(s, "&cThere are currently no CommandBlocks defined.");
                return true;
            }
            Utilities.msg(s, "&aA list of all CommandBlocks will be shown below.");
            Utilities.msg(s, "&7---");
            for (final String key : Utilities.blocks.getKeys(false)) {
                final String locString = Utilities.blocks.getString(key + ".location");
                assert locString != null;
                final String[] blockCoordinates = locString.split("#");
                final int x = Integer.parseInt(blockCoordinates[0]);
                final int y = Integer.parseInt(blockCoordinates[1]);
                final int z = Integer.parseInt(blockCoordinates[2]);
                final String world = blockCoordinates[3];
                Utilities.msg(s, "&fCommandBlock &9" + key + "&f at &6(" + x + "," + y + "," + z + ")&f in world &6'" + world + "'&f.");
            }
            Utilities.msg(s, "&7---");
            Utilities.msg(s, "&aA total of &f" + Utilities.blocks.getKeys(false).size() + "&a CommandBlocks are currently defined.");
        } else {
            Utilities.msg(s, Strings.LISTUSAGE);
        }
        return true;
    }

    public List<String> onTabComplete(CommandSender s, Command c, String label, String[] args) {
        ArrayList<String> tabs = new ArrayList<>();
        return CommandWrapper.filterTabs(tabs, args);
    }
}