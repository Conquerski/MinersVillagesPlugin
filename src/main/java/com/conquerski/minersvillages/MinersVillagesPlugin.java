package com.conquerski.minersvillages;

import com.conquerski.minersvillages.Listeners.MultipleDurabilityListener;
import com.conquerski.minersvillages.Listeners.NoMendingListener;
import com.conquerski.minersvillages.Gui.repairGUI;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinersVillagesPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginCommand("xlop").setExecutor((CommandExecutor) new CommandsClass());
        Bukkit.getPluginCommand("xl").setExecutor(new CommandsClass());
        Bukkit.getPluginCommand("xlgui").setExecutor(new repairGUI());

        Bukkit.getPluginManager().registerEvents(new NoMendingListener(), this);
        Bukkit.getPluginManager().registerEvents(new MultipleDurabilityListener(), this);


        setupEconomy();

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
