package com.conquerski.minersvillages;

import com.conquerski.minersvillages.Listeners.BlockBreakEventListener;
import com.conquerski.minersvillages.Listeners.MultipleDurabilityListener;
import com.conquerski.minersvillages.Listeners.NoMendingListener;
import com.conquerski.minersvillages.Gui.repairGUI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public final class MinersVillagesPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        Bukkit.getPluginCommand("xlop").setExecutor(new CommandsClass());
        Bukkit.getPluginCommand("xl").setExecutor(new CommandsClass());
        Bukkit.getPluginCommand("xlgui").setExecutor(new repairGUI());

        Bukkit.getPluginManager().registerEvents(new NoMendingListener(), this);
        Bukkit.getPluginManager().registerEvents(new MultipleDurabilityListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockBreakEventListener(), this);

        Setup.setupEcon();
        Setup.setupCoreProtect();
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
