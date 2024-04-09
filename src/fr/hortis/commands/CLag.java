package fr.hortis.commands;

import fr.hortis.utils.PREFIX;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class CLag implements CommandExecutor, ICommand {

    final API api;

    public CLag(API api) {
        this.api = api;
    }

    @Override
    public String getCommandName() {
        return "lag";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        int seconds = 5;

        if (args.length >= 1) {
            try {
                seconds = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                sender.sendMessage(PREFIX.ERROR + "§cVeuillez entrer un nombre de seconde valide !");
                return false;
            }
        }

        StringBuffer tps = new StringBuffer();
        double[] currentTps = api.getServerInfos().getTPSAverage(seconds);
        for (int i = 0; i < currentTps.length; i++) {
            if (i != 0)
                tps.append(", ");
            tps.append((int) currentTps[i]);
        }
        sender.sendMessage("");
        sender.sendMessage(ChatColor.DARK_GRAY + "» " + ChatColor.BLUE + "Infos du serveur");
        sender.sendMessage("");
        sender.sendMessage(ChatColor.DARK_GRAY + "» " + ChatColor.GRAY + "TPS: §a" + tps);
        if (sender instanceof Player) {
            sender.sendMessage(ChatColor.DARK_GRAY + "» " + ChatColor.GRAY + "Ping: §a" + (((CraftPlayer) sender).getHandle()).ping + " ms");
            sender.sendMessage("");
        }
        sender.sendMessage(ChatColor.DARK_GRAY + "» " + ChatColor.GRAY + "RAM: §a" + api.getServerInfos().getRam() + ChatColor.GRAY + "MB/" + ChatColor.RED + api.getServerInfos().getMaxRam() + ChatColor.GRAY + "MB");
        sender.sendMessage("");
        return true;
    }
}
