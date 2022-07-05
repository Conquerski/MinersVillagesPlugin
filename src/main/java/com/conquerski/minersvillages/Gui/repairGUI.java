package com.conquerski.minersvillages.Gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static com.conquerski.minersvillages.CommandsClass.repairCosts;


public class repairGUI implements CommandExecutor {
    static Gui gui = Gui.gui().title(Component.text("樱花村修理图形界面")).rows(3).create();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        openGUI(player);

        return true;
    }

    public static void openGUI(Player player) {


        GuiItem bluePane = ItemBuilder.from(Material.BLUE_STAINED_GLASS_PANE).asGuiItem();
        GuiItem greenPane = ItemBuilder.from(Material.GREEN_STAINED_GLASS_PANE).asGuiItem();
        GuiItem redPane = ItemBuilder.from(Material.RED_STAINED_GLASS_PANE).asGuiItem();
        GuiItem originalConfirmItem = ItemBuilder.from(Material.ANVIL).name(Component.text("§6✦ 确认修理 ✦")).asGuiItem();


        //GUI底色
        gui.getFiller().fill(bluePane);

        //输入栏样式
        gui.setItem(1, 3, redPane);
        gui.setItem(2, 2, redPane);
        gui.setItem(2, 4, redPane);
        gui.setItem(3, 3, redPane);
        gui.removeItem(2, 3);


        //输出栏样式
        gui.setItem(1, 7, greenPane);
        gui.setItem(2, 6, greenPane);
        gui.setItem(2, 8, greenPane);
        gui.setItem(3, 7, greenPane);
        gui.removeItem(2, 7);

        //确认按键
        gui.setItem(2, 5, originalConfirmItem);

        gui.setDefaultTopClickAction(event -> {
            if(event.getSlot() == toSlot(2,3) || event.getSlot() == toSlot(2,7)){

                if (event.getCursor() != null && event.getCursor().getType() != Material.AIR ) {
                    updateCosts(event.getCursor());
                }
                return;
            }
            event.setCancelled(true);
        });

        gui.open(player);
    }

    public static int toSlot(int row, int col) {
        return ((row - 1) * 8 + col);
    }
    public static void updateCosts(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        Damageable damageMeta = (Damageable) meta;
        int currentDamage = damageMeta.getDamage();
        Double costs = repairCosts(meta);

        GuiItem ConfirmItem = ItemBuilder.from(Material.ANVIL).name(Component.text("§6✦ 确认修理 ✦")).asGuiItem();
        ArrayList<String> ConfirmItemLore = new ArrayList<>();
        ItemStack ConfirmItemStack = ConfirmItem.getItemStack();
        ItemMeta newConfirmItemMeta = ConfirmItemStack.getItemMeta();
        ConfirmItemLore.add(0, "=========");
        ConfirmItemLore.add(1, "§f当前损耗耐久：§c" + currentDamage);
        ConfirmItemLore.add(2, "§f修复所需花费：§b" + costs);
        newConfirmItemMeta.setLore(ConfirmItemLore);
        ConfirmItemStack.setItemMeta(newConfirmItemMeta);

        ConfirmItem = ItemBuilder.from(ConfirmItemStack).asGuiItem();
        gui.setItem(2, 5, ConfirmItem);



    }

}
