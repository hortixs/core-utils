package fr.hortis.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EJoin implements Listener {

    final API api;

    public EJoin(API api) {
        this.api = api;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        User user = api.getUserManager().getUser(event.getPlayer().getUniqueId());
        if (user != null)
            event.getPlayer().sendMessage("§7");

        else {
            api.getUserManager().createUser(event.getPlayer());
            event.getPlayer().sendMessage(Messages.PREFIX + "§7Bienvenu(e) à toi !");
        }
    }
}
