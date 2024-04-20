package fr.hortis;

import fr.hortis.commands.*;
import fr.hortis.commands.coins.CCoins;
import fr.hortis.commands.coins.CVersaCoins;
import fr.hortis.commands.rank.CRank;
import fr.hortis.listeners.EJoin;
import fr.hortis.listeners.EMention;
import fr.hortis.listeners.EWeather;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Setup {

    final JavaPlugin plugin;
    final VersaAPI api;

    public Setup(JavaPlugin plugin, VersaAPI api) {
        this.plugin = plugin;
        this.api = api;
        init();
    }

    private void init() {
        api.getCommandManager().registerCommand(plugin, new CClearChat());
        api.getCommandManager().registerCommand(plugin, new CDiscord());
        api.getCommandManager().registerCommand(plugin, new CFly());
        api.getCommandManager().registerCommand(plugin, new CFeed());
        api.getCommandManager().registerCommand(plugin, new CHeal());
        api.getCommandManager().registerCommand(plugin, new CHead());
        api.getCommandManager().registerCommand(plugin, new CHelp());
        api.getCommandManager().registerCommand(plugin, new CPing());
        api.getCommandManager().registerCommand(plugin, new CPvp());
        api.getCommandManager().registerCommand(plugin, new CClear());
        api.getCommandManager().registerCommand(plugin, new CTp());
        api.getCommandManager().registerCommand(plugin, new CTpAll());
        api.getCommandManager().registerCommand(plugin, new CRank());
        api.getCommandManager().registerCommand(plugin, new CGm());
        api.getCommandManager().registerCommand(plugin, new CCheck());
        api.getCommandManager().registerCommand(plugin, new CVersaCoins());
        api.getCommandManager().registerCommand(plugin, new CCoins());
        api.getCommandManager().registerCommand(plugin, new CLag(api));
        Bukkit.getPluginManager().registerEvents(new EJoin(api), plugin);
        Bukkit.getPluginManager().registerEvents(new EMention(api), plugin);
        Bukkit.getPluginManager().registerEvents(new EWeather(api), plugin);
    }

    public VersaAPI getApi() {
        return api;
    }
}
