package com.conquerski.minersvillages.Implement;


import org.bukkit.block.Block;

import static com.conquerski.minersvillages.Implement.RandomDr.dr;
import static com.conquerski.minersvillages.Setup.api;


public class MiningRandom{

    public Boolean checkNaturalized(Block block){
        return (api.blockLookup(block,(int)(System.currentTimeMillis()/1000L)).isEmpty());
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
