package com.conquerski.minersvillages.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemMendEvent;

/**
 * 取消经验修补事件的监听器
 *
 * @author ConquerSki
 */

public class NoMendingListener implements Listener {
    @EventHandler
    public void noMending(PlayerItemMendEvent e){
        e.setCancelled(true);
        String a = e.getPlayer().getName();
    }
}

