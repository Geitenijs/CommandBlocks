package com.geitenijs.commandblocks;

import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

class Hooks {

    static boolean Vault;
    static boolean incompatibleVault;
    static Economy econ = null;

    static void registerHooks() {
        hookVault();
    }

    private static void hookVault() {
        final Plugin vaultPlugin = Bukkit.getPluginManager().getPlugin("Vault");
        if (!(vaultPlugin instanceof Vault)) {
            Vault = false;
            incompatibleVault = false;
            if (Utilities.config.getBoolean("general.debug")) {
                Utilities.consoleMsgPrefixed(Strings.DEBUGPREFIX + Strings.DEPENDENCIES_VAULT_MISSING);
            }
            return;
        }
        String exactVaultVersion = Bukkit.getServer().getPluginManager().getPlugin("Vault").getDescription().getVersion();
        int vaultVersion;
        try {
            vaultVersion = Integer.parseInt(exactVaultVersion.replace(".", "").substring(0, 3));
        } catch (Exception ex) {
            Vault = false;
            incompatibleVault = true;
            if (Utilities.config.getBoolean("general.debug")) {
                Utilities.consoleMsgPrefixed(Strings.DEBUGPREFIX + Strings.DEPENDENCIES_VAULT_INCOMPATIBLE);
            }
            return;
        }
        if (vaultVersion >= 170) {
            Vault = true;
            incompatibleVault = false;
            if (Utilities.config.getBoolean("general.debug")) {
                Utilities.consoleMsgPrefixed(Strings.DEBUGPREFIX + Strings.DEPENDENCIES_VAULT_COMPATIBLE);
            }
            setupEconomy();
        } else {
            Vault = false;
            incompatibleVault = true;
            if (Utilities.config.getBoolean("general.debug")) {
                Utilities.consoleMsgPrefixed(Strings.DEBUGPREFIX + Strings.DEPENDENCIES_VAULT_INCOMPATIBLE);
            }
        }
    }

    private static boolean setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return true;
    }
}
