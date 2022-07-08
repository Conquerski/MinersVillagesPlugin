package com.conquerski.minersvillages;

import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import static org.bukkit.Bukkit.getServer;

/**
 * @author ConquerSki
 * @create 2022/7/8 15:43
 */
public final class Setup {
    public static Economy econ;
    public static CoreProtectAPI api;
    public static void setupEcon(){
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return;
        }
        econ = rsp.getProvider();
    }
    public static void setupCoreProtect(){
        api = getCoreProtect();
        if (api != null){ // Ensure we have access to the API
            api.testAPI(); // Will print out "[CoreProtect] API test successful." in the console.
        }
    }
    public static CoreProtectAPI getCoreProtect() {
        Plugin plugin = getServer().getPluginManager().getPlugin("CoreProtect");
        // Check that CoreProtect is loaded
        if (!(plugin instanceof CoreProtect)) {
            return null;
        }
        // Check that the API is enabled
        CoreProtectAPI CoreProtect = ((CoreProtect) plugin).getAPI();
        if (!CoreProtect.isEnabled()) {
            return null;
        }
        // Check that a compatible version of the API is loaded
        if (CoreProtect.APIVersion() < 9) {
            return null;
        }
        return CoreProtect;
    }

}
