package com.conquerski.minersvillages;


import com.conquerski.minersvillages.Implement.repairItems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;


public class CommandsClass implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {
        Player player = (Player) sender;
        String commandName = command.getName().toLowerCase();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (commandName.equals("xl")) {
            return repairItems.repairIt(item,player);
        }
        return false;
    }
}
