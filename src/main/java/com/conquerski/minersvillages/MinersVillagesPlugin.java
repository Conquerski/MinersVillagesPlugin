package com.conquerski.minersvillages;

import com.conquerski.minersvillages.Listeners.BlockBreakEventListener;
import com.conquerski.minersvillages.Listeners.MultipleDurabilityListener;
import com.conquerski.minersvillages.Listeners.NoMendingListener;
import com.conquerski.minersvillages.Gui.repairGUI;
import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinersVillagesPlugin extends JavaPlugin {
    public CoreProtectAPI api = getCoreProtect();
    @Override
    public void onEnable() {
        // Plugin startup logic

        Bukkit.getPluginCommand("xlop").setExecutor(new CommandsClass());
        Bukkit.getPluginCommand("xl").setExecutor(new CommandsClass());
        Bukkit.getPluginCommand("xlgui").setExecutor(new repairGUI());

        Bukkit.getPluginManager().registerEvents(new NoMendingListener(), this);
        Bukkit.getPluginManager().registerEvents(new MultipleDurabilityListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockBreakEventListener(), this);


        if (api != null){ // Ensure we have access to the API
            api.testAPI(); // Will print out "[CoreProtect] API test successful." in the console.
        }
        setupEconomy();

    }
    private CoreProtectAPI getCoreProtect() {
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

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Economy econ = null;

    private void setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return;
        }
        econ = rsp.getProvider();
    }
}
