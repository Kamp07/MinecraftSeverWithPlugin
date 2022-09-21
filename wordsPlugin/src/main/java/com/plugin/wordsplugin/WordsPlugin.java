package com.plugin.wordsplugin;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.event.server.BroadcastMessageEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.bukkit.Bukkit.broadcastMessage;

public final class WordsPlugin extends JavaPlugin implements Listener {

    public static int tid = 0;

    long UUID;

    Player player;

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Plugin started, Hello!");
        getServer().getPluginManager().registerEvents(this, this);

        tid = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                try {
                    broadcastMessage(getMessage("your path\\MinecraftServer\\plugins\\phrases.txt"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1000);

    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        System.out.println("A player has joined the server !");

        UUID = event.getPlayer().getEntityId();

        player = event.getPlayer();

        event.setJoinMessage("Welcome to the server !!!");

        createPhrasesThread(player).start();


    }

    public static String getMessage(String fileName) throws IOException {
        File file = new File(fileName);

        String[] content = FileUtils.readFileToString(file, "UTF-8").split(";");

        List<String> messages = Arrays.asList(content);

        return messages.get((int) (Math.random() * content.length));
    }

    public Thread createPhrasesThread(Player player) {
        return new SendPhrasesThread(player);
    }
}
