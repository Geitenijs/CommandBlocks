package com.geitenijs.commandblocks;

import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

class Hooks {

    static boolean Vault;
    static Economy eco = null;

    static void registerHooks() {
        hookVault();
    }

    private static void hookVault() {
        final Plugin vaultPlugin = Bukkit.getPluginManager().getPlugin("Vault");
        if (!(vaultPlugin instanceof Vault)) {
            Vault = false;
            return;
        }
        String exactVaultVersion = Bukkit.getServer().getPluginManager().getPlugin("Vault").getDescription().getVersion();
        int vaultVersion;
        try {
            vaultVersion = Integer.parseInt(exactVaultVersion.replace(".", "").substring(0, 3));
        } catch (Exception ex) {
            Vault = false;
            Utilities.consoleMsg(Strings.INTERNALPREFIX + Strings.DEPENDENCIES_VAULT_INCOMPATIBLE);
            return;
        }
        if (vaultVersion >= 170) {
            Vault = true;
            setupEconomy();
        } else {
            Vault = false;
        }
    }

    private static boolean setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        eco = rsp.getProvider();
        return eco != null;
    }
}
