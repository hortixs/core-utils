package fr.hortis.commands;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CClearChat implements CommandExecutor, ICommand {

    @Override
    public String getCommandName() {
        return "clearchat";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (!sender.hasPermission("core.mod")) {
            sender.sendMessage("Commande inconnue.");
            return false;
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (int i = 0; i < 1000; i++)
                player.sendMessage(" ");
        }
        Bukkit.broadcastMessage(Messages.PREFIX + ChatColor.GRAY + "Le chat vient d'être clear par un membre du staff");
        return true;
    }
}
