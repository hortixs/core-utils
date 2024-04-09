package fr.hortis;

import io.rqndomhax.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Core extends JavaPlugin implements Listener {

    public static Core manager;

    @Override
    public void onEnable() {
        manager = this;
        super.onEnable();
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Chargement de l'API ...");
        //Optimisation du plugin
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(16);
        ScheduledExecutorService executorMonoThread = Executors.newScheduledThreadPool(1);
    }
    private ScheduledExecutorService getExecutorMonoThread() {
        return executorMonoThread;
    }

    private ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }

}
