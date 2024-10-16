package com.geitenijs.commandblocks;

import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Objects;

public class Events implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommandBlockInteract(PlayerInteractEvent e) {

        if (((e.getClickedBlock() != null && e.getAction() == Action.RIGHT_CLICK_BLOCK) && (!e.getClickedBlock().getType().name().endsWith("_PLATE"))) || (((e.getAction() == Action.PHYSICAL)) && (e.getClickedBlock().getType().name().endsWith("_PLATE")))) {
            EquipmentSlot es = e.getHand();
            assert es != null;
            if (!(((e.getAction() == Action.PHYSICAL)) && (e.getClickedBlock().getType().name().endsWith("_PLATE")))) {
                if (es.equals(EquipmentSlot.OFF_HAND)) {
                    return;
                }
            }

            Location blockLocation = e.getClickedBlock().getLocation();
            String convertedBlockLocation = blockLocation.getBlockX() + "#" + blockLocation.getBlockY() + "#" + blockLocation.getBlockZ() + "#" + blockLocation.getWorld().getName();
            final boolean[] passEco = {false};
            for (final String path : Utilities.blocks.getKeys(false)) {
                if (Utilities.blocks.getString(path + ".location") != null && Objects.equals(Utilities.blocks.getString(path + ".location"), convertedBlockLocation)) {
                    String permission = Utilities.blocks.getString(path + ".permission.value");
                    if (permission == null || e.getPlayer().hasPermission(permission)) {
                        Bukkit.getScheduler().runTaskLater(Main.plugin, () -> {
                            String timeoutBypass = Utilities.blocks.getString(path + ".timeout.bypasspermission");
                            if (timeoutBypass == null || !e.getPlayer().hasPermission(timeoutBypass)) {
                                if (Utilities.timeouts.containsKey(path)) {
                                    double timeLeft = (Utilities.timeouts.get(path) / 20);
                                    int output = (int) Math.ceil(timeLeft);
                                    String finalOutput = Integer.toString(output);
                                    if (!Utilities.blocks.getStringList(path + ".timeout.commands.console").isEmpty()) {
                                        for (String timeoutCommandsConsole : Utilities.blocks.getStringList(path + ".timeout.commands.console")) {
                                            timeoutCommandsConsole = timeoutCommandsConsole.replace("{player}", e.getPlayer().getName());
                                            timeoutCommandsConsole = timeoutCommandsConsole.replace("{time}", finalOutput);
                                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), timeoutCommandsConsole);
                                        }
                                    }
                                    if (!Utilities.blocks.getStringList(path + ".timeout.commands.player").isEmpty()) {
                                        for (String timeoutCommandsPlayer : Utilities.blocks.getStringList(path + ".timeout.commands.player")) {
                                            timeoutCommandsPlayer = timeoutCommandsPlayer.replace("{player}", e.getPlayer().getName());
                                            timeoutCommandsPlayer = timeoutCommandsPlayer.replace("{time}", finalOutput);
                                            Bukkit.dispatchCommand(e.getPlayer(), timeoutCommandsPlayer);
                                        }
                                    }
                                    if (!Utilities.blocks.getStringList(path + ".timeout.messages").isEmpty()) {
                                        for (String timeoutMessages : Utilities.blocks.getStringList(path + ".timeout.messages")) {
                                            timeoutMessages = timeoutMessages.replace("{player}", e.getPlayer().getName());
                                            timeoutMessages = timeoutMessages.replace("{time}", finalOutput);
                                            Utilities.msg(e.getPlayer(), timeoutMessages);
                                        }
                                    }
                                    return;
                                }
                            }

                            if (Utilities.blocks.getDouble(path + ".cost.value") != 0) {
                                String costBypass = Utilities.blocks.getString(path + ".cost.bypasspermission");
                                if (costBypass != null && e.getPlayer().hasPermission(costBypass)) {
                                    passEco[0] = true;
                                }
                                if (!passEco[0]) {
                                    if (Hooks.Vault) {
                                        EconomyResponse r = Hooks.eco.withdrawPlayer(e.getPlayer(), Utilities.blocks.getDouble(path + ".cost.value"));
                                        if (r.transactionSuccess()) {
                                            passEco[0] = true;
                                        } else {
                                            if (!Utilities.blocks.getStringList(path + ".cost.commands.console").isEmpty()) {
                                                for (String costCommandsConsole : Utilities.blocks.getStringList(path + ".cost.commands.console")) {
                                                    costCommandsConsole = costCommandsConsole.replace("{player}", e.getPlayer().getName());
                                                    costCommandsConsole = costCommandsConsole.replace("{cost}", String.valueOf(Utilities.blocks.getDouble(path + ".cost.value")));
                                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), costCommandsConsole);
                                                }
                                            }
                                            if (!Utilities.blocks.getStringList(path + ".cost.commands.player").isEmpty()) {
                                                for (String costCommandsPlayer : Utilities.blocks.getStringList(path + ".cost.commands.player")) {
                                                    costCommandsPlayer = costCommandsPlayer.replace("{player}", e.getPlayer().getName());
                                                    costCommandsPlayer = costCommandsPlayer.replace("{cost}", String.valueOf(Utilities.blocks.getDouble(path + ".cost.value")));
                                                    Bukkit.dispatchCommand(e.getPlayer(), costCommandsPlayer);
                                                }
                                            }
                                            if (!Utilities.blocks.getStringList(path + ".cost.messages").isEmpty()) {
                                                for (String costMessages : Utilities.blocks.getStringList(path + ".cost.messages")) {
                                                    costMessages = costMessages.replace("{player}", e.getPlayer().getName());
                                                    costMessages = costMessages.replace("{cost}", String.valueOf(Utilities.blocks.getDouble(path + ".cost.value")));
                                                    Utilities.msg(e.getPlayer(), costMessages);
                                                }
                                            }
                                        }
                                    } else {
                                        Utilities.msg(e.getPlayer(), Strings.NOVAULT);
                                    }
                                }
                            } else {
                                passEco[0] = true;
                            }

                            if (passEco[0]) {
                                if (!Utilities.blocks.getStringList(path + ".success.commands.console").isEmpty()) {
                                    for (String successCommandsConsole : Utilities.blocks.getStringList(path + ".success.commands.console")) {
                                        successCommandsConsole = successCommandsConsole.replace("{player}", e.getPlayer().getName());
                                        successCommandsConsole = successCommandsConsole.replace("{cost}", String.valueOf(Utilities.blocks.getDouble(path + ".cost.value")));
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), successCommandsConsole);
                                    }
                                }
                                if (!Utilities.blocks.getStringList(path + ".success.commands.player").isEmpty()) {
                                    for (String successCommandsPlayer : Utilities.blocks.getStringList(path + ".success.commands.player")) {
                                        successCommandsPlayer = successCommandsPlayer.replace("{player}", e.getPlayer().getName());
                                        successCommandsPlayer = successCommandsPlayer.replace("{cost}", String.valueOf(Utilities.blocks.getDouble(path + ".cost.value")));
                                        Bukkit.dispatchCommand(e.getPlayer(), successCommandsPlayer);
                                    }
                                }
                                if (!Utilities.blocks.getStringList(path + ".success.messages").isEmpty()) {
                                    for (String successMessages : Utilities.blocks.getStringList(path + ".success.messages")) {
                                        successMessages = successMessages.replace("{player}", e.getPlayer().getName());
                                        successMessages = successMessages.replace("{cost}", String.valueOf(Utilities.blocks.getDouble(path + ".cost.value")));
                                        Utilities.msg(e.getPlayer(), successMessages);
                                    }
                                }
                            }

                            Utilities.timeouts.put(path, Utilities.blocks.getDouble(path + ".timeout.value") * 20);
                        }, Utilities.blocks.getLong(path + ".delay.value") * 20);
                    } else {
                        if (!Utilities.blocks.getStringList(path + ".permission.commands.console").isEmpty()) {
                            for (String permissionCommandsConsole : Utilities.blocks.getStringList(path + ".permission.commands.console")) {
                                permissionCommandsConsole = permissionCommandsConsole.replace("{player}", e.getPlayer().getName());
                                permissionCommandsConsole = permissionCommandsConsole.replace("{permission}", permission);
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), permissionCommandsConsole);
                            }
                        }
                        if (!Utilities.blocks.getStringList(path + ".permission.commands.player").isEmpty()) {
                            for (String permissionCommandsPlayer : Utilities.blocks.getStringList(path + ".permission.commands.player")) {
                                permissionCommandsPlayer = permissionCommandsPlayer.replace("{player}", e.getPlayer().getName());
                                permissionCommandsPlayer = permissionCommandsPlayer.replace("{permission}", permission);
                                Bukkit.dispatchCommand(e.getPlayer(), permissionCommandsPlayer);
                            }
                        }
                        if (!Utilities.blocks.getStringList(path + ".permission.messages").isEmpty()) {
                            for (String permissionMessages : Utilities.blocks.getStringList(path + ".permission.messages")) {
                                permissionMessages = permissionMessages.replace("{player}", e.getPlayer().getName());
                                permissionMessages = permissionMessages.replace("{permission}", permission);
                                Utilities.msg(e.getPlayer(), permissionMessages);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onUpdateAvailable(PlayerJoinEvent e) {
        if ((e.getPlayer().hasPermission("commandblocks.notify.update")) && Utilities.config.getBoolean("updates.check") && Utilities.config.getBoolean("updates.notify") && Utilities.updateAvailable()) {
            Bukkit.getScheduler().runTaskLaterAsynchronously(Main.plugin, () -> {
                Utilities.msg(e.getPlayer(), Strings.IGPREFIX + "&fA new &a" + Strings.PLUGIN + "&f update is available: &av" + Utilities.updateVersion() + "&f!");
                Utilities.msg(e.getPlayer(), Strings.IGPREFIX + "&fDownload @ &a" + Strings.WEBSITE + "&f.");
            }, 100L);
        }
    }
}