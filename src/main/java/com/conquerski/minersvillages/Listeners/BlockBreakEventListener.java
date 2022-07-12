package com.conquerski.minersvillages.Listeners;

import com.conquerski.minersvillages.Implement.MiningRandom;
import com.conquerski.minersvillages.Implement.PlayerSendMsg;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;


/**
 * 破坏方块事件
 *
 * @author ConquerSki
 * @create 2022/7/8 13:50
 */
public class BlockBreakEventListener implements Listener {
    MiningRandom m = new MiningRandom();
    @EventHandler
    public void miningRandom(BlockBreakEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        Block block = event.getBlock();
        Location location = block.getLocation();
        if(m.miningRandom(400,"stone", block)){
            world.dropItemNaturally(location, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));//奖励附魔金苹果
            PlayerSendMsg.SendMsgMining(player,1,"附魔金苹果");
        }
        if(m.miningRandom(400,"deepslate", block)){
            world.dropItemNaturally(location, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));//奖励附魔金苹果
            PlayerSendMsg.SendMsgMining(player,1,"附魔金苹果");
        }
        if(m.miningRandom(200,"stone", block)){
            event.setExpToDrop(50);
            PlayerSendMsg.SendMsgMining(player,50,"Exp");
        }




    }
    //检测自然放置

}
