package fr.hortis.listeners;


import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class EMention implements Listener {

    final API api;

    public EMention(API api) {
        this.api = api;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent e) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            if (e.getMessage().contains(player.getName())) {
                player.sendMessage("§c" + player.getName() + " §7vous a mentionné.");
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 2.0F, 0.0F);
                e.setCancelled(true);
            }
        }
    }
}


