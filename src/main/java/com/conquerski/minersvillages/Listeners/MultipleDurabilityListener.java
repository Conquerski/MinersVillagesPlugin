package com.conquerski.minersvillages.Listeners;

import com.conquerski.minersvillages.Implement.DurabilityRandom;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;

import static com.conquerski.minersvillages.Implement.RandomDr.dr;

public class MultipleDurabilityListener implements Listener {

    DurabilityRandom dRandom = new DurabilityRandom();
    @EventHandler
    public void calDurability(PlayerItemDamageEvent event) {
        int damage = event.getDamage();//damage 本次事件即将增加的消耗耐久
        int newDamage = 0;
        String name = event.getItem().getType().toString();//物品名称
        //根据品阶制定消耗倍率
        if (name.contains("NETHERITE")) {
            newDamage = 6 * damage;
        } else if (name.contains("DIAMOND")) {
            newDamage = 5 * damage;
        } else if (name.contains("IRON")) {
            newDamage = 3 * damage;
        } else if (name.contains("GOLDEN")) {
            newDamage = damage;
        } else if (name.contains("STONE")) {
            newDamage = 2 * damage;
        } else if (name.contains("WOODEN")) {
            newDamage = damage;
        } else if (name.contains("ELYTRA")) {
            newDamage = damage;
        } else {
            newDamage = damage;
        }
        event.setDamage(newDamage);

        //随机事件
        int r = dr.nextInt(6);
        switch (r) {
            case 1:
                dRandom.durabilityRandom(300, 30, event);
                break;
            case 2:
                dRandom.durabilityRandom(300, -30, event);
                break;
            case 3:
                dRandom.durabilityRandom(150, 10, event);
                break;
            case 4:
                dRandom.durabilityRandom(150, -10, event);
                break;
            case 5:
                dRandom.durabilityRandom(200, 20, event);
                break;
            case 6:
                dRandom.durabilityRandom(200, -20, event);
                break;

        }
    }
}
