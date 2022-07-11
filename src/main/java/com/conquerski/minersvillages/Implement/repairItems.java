package com.conquerski.minersvillages.Implement;

import com.conquerski.minersvillages.CommandsClass;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DecimalFormat;

import static com.conquerski.minersvillages.Setup.econ;
import static org.bukkit.enchantments.Enchantment.MENDING;
import static org.bukkit.enchantments.Enchantment.DURABILITY;

/**
 * @author ConquerSki
 * @create 2022/7/11 16:26
 */
public class repairItems{

    public static boolean repairIt(ItemStack item, Player player) {

        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            player.sendMessage("§6[樱花村修复]：§c你需要把要修复的物品拿在手上！");
            return false;
        }
        double balance = econ.getBalance(player);
        double cost = calCosts(item);
        String text;
        if (balance >= cost) {
            if (meta instanceof Damageable){
                Damageable damageMeta = (Damageable) meta;
                if(damageMeta.getDamage()!=0){
                    damageMeta.setDamage(0);
                    item.setItemMeta(damageMeta);
                    player.performCommand("pay tax " + cost);
                    balance = econ.getBalance(player);
                    text = "§6[樱花村修复]：§d你手里的物品已完好如初！本次修复花费§c" + cost + "§d元，你的账户余额还剩§b" + balance + "§d元。";
                    player.sendMessage(text);
                    return true;
                }else {
                    text = "§6[樱花村修复]：§d你手里的物品看上去不需要修理！本次修复花费§c" + cost + "§d元，你的账户余额还剩§b" + balance + "§d元。";
                    player.sendMessage(text);
                    return false;
                }
            }
        } else {
            text = "§6[樱花村修复]：§c你身上的钱不够本次修理费用！本次修复需花费" + cost + "元，你的账户余额仅剩" + balance + "元。";
            player.sendMessage(text);
        }
        return false;
    }

    public static double calCosts(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        Damageable damageMeta = (Damageable) meta;
        int currentDamage = damageMeta.getDamage();
        int quantity;
        int coe = meta.getEnchantLevel(DURABILITY);
        double typeCoe;
        String type = item.getType().toString().toLowerCase();

        if (type.contains("diamond")){
            typeCoe = 1.2;
        } else if (type.contains("netherite")) {
            typeCoe = 1.5;
        } else {
            typeCoe = 1;
        }
        if (meta.getEnchantLevel(MENDING) == 1){
            quantity = meta.getEnchants().size() - 1;
        }else {
            quantity = meta.getEnchants().size();
        }
        if (coe != 0){
            quantity = quantity - 1;
        }
        double result = (quantity+1) * (coe+1) * currentDamage * typeCoe * 0.03;
        DecimalFormat number = new DecimalFormat("#.00");
        result = Double.parseDouble(number.format(result));
        return(result);
    }
}
