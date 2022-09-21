package com.plugin.wordsplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.IOException;

public class SendPhrasesThread extends Thread{

    Player player;

    public SendPhrasesThread(Player player) {
        this.player = player;
    }

    @Override
    public void run(){

        long mills;

        for (;;) {

            try {
                mills = (long) (2000 + Math.random() * 8000);
                player.sendMessage(ChatColor.DARK_PURPLE + WordsPlugin.getMessage("C:\\Users\\kiril\\IdeaProjects\\Minecraft Server With Plugin\\MinecraftServer\\plugins\\phrases.txt"));
                Thread.sleep(mills);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
