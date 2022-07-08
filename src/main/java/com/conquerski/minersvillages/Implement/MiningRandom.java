package com.conquerski.minersvillages.Implement;

import com.conquerski.minersvillages.MinersVillagesPlugin;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;

import static com.conquerski.minersvillages.Implement.RandomDr.dr;



public class MiningRandom{
    MinersVillagesPlugin m = new MinersVillagesPlugin();
    public Boolean checkNaturalized(Block block){
        return (m.api.blockLookup(block,(int)(System.currentTimeMillis()/1000L)).isEmpty());
    }

    public Boolean miningRandom(Integer probability, String blockBreakName, Block block) {
        String blockName = block.getType().toString().toLowerCase();

        if(blockName.contains(blockBreakName)){
            if(dr.nextInt(probability) == 1){
                return checkNaturalized(block);
            }
        }
        return false;
    }
}
