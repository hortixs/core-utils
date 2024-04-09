package fr.hortis.listeners;


import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class EWeather implements Listener {

    final API api;

    public EWeather(API api) {
        this.api = api;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWeather(WeatherChangeEvent event) {
        event.setCancelled(true);
        }
    }

