package com.conquerski.minersvillages;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import java.text.DecimalFormat;

import static com.conquerski.minersvillages.Setup.econ;
import static org.bukkit.enchantments.Enchantment.MENDING;


public class CommandsClass implements CommandExecutor {
    ItemStack item;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        String commandName = command.getName().toLowerCase();
        item = player.getInventory().getItemInMainHand();
        ItemMeta meta  = item.getItemMeta();
        //System.out.println(meta);
        if (meta == null){
            player.sendMessage("§6[樱花村修复]：§c你需要把要修复的物品拿在手上！");
            return(false);
        }
        if(commandName.equals("xlop")){
            if(repairItems(meta)){
                player.sendMessage("§6[OP修复]：§c你手里的物品已完好如初！");
            }else{
                player.sendMessage("§6[OP修复]：§c你手里的物品看上去不需要修理！");
            }
        } else if (commandName.equals("xl")) {
            double balance = econ.getBalance(player);
            //System.out.println(balance);

            double cost = repairCosts(meta);
            String text;
            if(balance >= cost){
                if(repairItems(meta)){
                    player.performCommand("pay tax "+cost);
                    balance = econ.getBalance(player);
                    text = "§6[樱花村修复]：§d你手里的物品已完好如初！本次修复花费§c"+cost+"§d元，你的账户余额还剩§b"+balance+"§d元。";
                    player.sendMessage(text);
                }else{
                    balance = econ.getBalance(player);
                    text = "§6[樱花村修复]：§d你手里的物品看上去不需要修理！本次修复花费§c"+cost+"§d元，你的账户余额还剩§b"+balance+"§d元。";
                    player.sendMessage(text);
                }
            }else{
                balance = econ.getBalance(player);
                text = "§6[樱花村修复]：§c你身上的钱不够本次修理费用！本次修复需花费"+cost+"元，你的账户余额仅剩"+balance+"元。";
                player.sendMessage(text);
            }


        }

        return true;
    }
    public boolean repairItems(ItemMeta meta){

        if (meta instanceof Damageable){
            Damageable damageMeta = (Damageable) meta;
            if(damageMeta.getDamage()!=0){
                damageMeta.setDamage(0);
                item.setItemMeta(damageMeta);

                return true;

            }else {
                return false;
            }
        }
        return false;
    }

    public static double repairCosts(ItemMeta meta) {
        int quantity = meta.getEnchants().size();
        int mendingLevel = meta.getEnchantLevel(MENDING);

        if (mendingLevel == 1){
            quantity = quantity - 1;
        }

        int coefficient = quantity + 1;
        Damageable damageMeta = (Damageable) meta;
        int currentDamage = damageMeta.getDamage();


        double result = coefficient * currentDamage * 0.03;
        DecimalFormat dr = new DecimalFormat("#.00");
        result = Double.parseDouble(dr.format(result));
        return(result);

    }




}
