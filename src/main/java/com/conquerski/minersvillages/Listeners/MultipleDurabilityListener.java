package com.conquerski.minersvillages.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

public class MultipleDurabilityListener implements Listener {
    Random dr = new Random();
    @EventHandler
    public void calDurability(PlayerItemDamageEvent e){
        Player player = e.getPlayer();//获取玩家信息
        ItemStack item = e.getItem();//item 受影响的物品ItemStack类型
        ItemMeta itemMeta = item.getItemMeta();//itemMeta 物品Meta
        Damageable damageableMeta = (Damageable) itemMeta;//damageableMeta
        int existDamage = damageableMeta.getDamage();//existDamage 物品已经存在的消耗耐久
        int damage = e.getDamage();//damage 本次事件即将增加的消耗耐久
        int maxDurability = e.getItem().getType().getMaxDurability();//maxDurability 物品最大耐久度
        String name = e.getItem().getType().toString();//物品名称
        int newDamage = 0;
        //根据品阶制定消耗倍率
        if (name.contains("NETHERITE")){
            newDamage = 6 * damage;
        }else if (name.contains("DIAMOND")){
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
        } else{
            newDamage = damage;
        }
        itemMeta.addItemFlags(ItemF);

        int r = dr.nextInt(300);
        if (r == 1){//0.5%
            if(existDamage >= 50){
                damageableMeta.setDamage( existDamage - 50);
                item.setItemMeta(damageableMeta);
                player.sendMessage("§2[LuckyOrNot]§6你真是太幸运了，触发隐藏事件你的 " + name + " 获得§b 50 §6点耐久！");
                e.setDamage(0);
                return;
            } else if (existDamage != 0) {
                damageableMeta.setDamage(0);
                item.setItemMeta(damageableMeta);
                player.sendMessage("§2[LuckyOrNot]§6你真是太幸运了，触发隐藏事件你的 " + name + " 获得§b 50 §6点耐久！");
                e.setDamage(0);
                return;
            }

        } else if (r == 2 || r == 3) {//1%
            if (existDamage + 10 < maxDurability) {
                player.sendMessage("§2[LuckyOrNot]§c你运气太差了，触发隐藏事件你的 " + name + " 多消耗了§b 10 §6点耐久！");
                e.setDamage(10);
            }
        } else if (r == 4 || r == 5) {//1%
            if(existDamage >= 10){
                damageableMeta.setDamage( existDamage - 10);
                item.setItemMeta(damageableMeta);
                player.sendMessage("§2[LuckyOrNot]§6你真是太幸运了，触发隐藏事件你的 " + name + " 获得§b 10 §6点耐久！");
                e.setDamage(0);
                return;
            }else if (existDamage != 0) {
                damageableMeta.setDamage(0);
                item.setItemMeta(damageableMeta);
                player.sendMessage("§2[LuckyOrNot]§6你真是太幸运了，触发隐藏事件修好你的 " + name + " ！");
                e.setDamage(0);
                return;
            }
        } else if (r == 6) {//0.5%
            if (existDamage + 20 < maxDurability) {
                player.sendMessage("§2[LuckyOrNot]§c你运气太差了，触发隐藏事件你的 " + name + " 多消耗了§b 20 §6点耐久！");
                e.setDamage(20);
            }
        }

        if (maxDurability - existDamage <= newDamage ){
            player.sendMessage("§c你的物品即将破损，手持需修复物品输入指令§b /xl §6修复！");
            return;
        }

        if (newDamage + existDamage <= maxDurability) {
            e.setDamage(newDamage);
        }else{
            player.sendMessage("§c你的物品即将破损，手持需修复物品输入指令§b /xl §6修复！");
            damageableMeta.setDamage(maxDurability - 1);
            item.setItemMeta(damageableMeta);
        }


    }
    public void luckyOrNot(int a,int durabilityAmount,Player player,String name){
        //分母为a
        if(a > 1000){
            return;
        }
        int r = dr.nextInt(a);
        if(r == a){
            player.sendMessage("§2[LuckyOrNot]§6你真是太幸运了，触发隐藏事件你的 " + name + " 获得§b 50 §6点耐久！");
        }

    }

}
