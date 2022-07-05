package com.conquerski.minersvillages.Implement;

import org.bukkit.entity.Player;

public class PlayerSendMsg {
    /**
     *
     * @param player "接收信息的玩家参数"
     * @param amount “随机事件带来的数值”
     * @param name “影响物品的名称，耐久度事件必填参数，经验事件填Exp“
     *
     * @author ConquerSki
     */
    public static void SendMsg(Player player, Integer amount, String name) {
        String suffix;
        String sendName;
        if (name.equals("Exp")){
            sendName = "经验值";
            suffix = "！";
        }else{
            sendName = name;
            suffix = "耐久！";
        }

        String luckyMsg = "§2[隐藏事件]§6你真是太幸运了， §b" + sendName + "§2 增加了§b " + amount + " §6" + suffix;
        String unluckyMsg = "§2[隐藏事件]§6你运气太差了了， §b" + sendName + "§2 减少了§b " + Math.abs(amount) + " §6" + suffix;
        if (amount > 0){
            player.sendMessage(luckyMsg);
        } else if (amount < 0) {
            player.sendMessage(unluckyMsg);
        }
    }
}
