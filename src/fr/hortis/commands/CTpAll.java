package fr.hortis.commands;

import fr.hortis.utils.PREFIX;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CTpAll implements CommandExecutor, ICommand {

    @Override
    public String getCommandName() {
        return "tpall";
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Bukkit.getServer().getOnlinePlayers().size() == 1) {
                player.sendMessage(PREFIX.ERROR + "§cAucun joueurs connectés.");
            } else if (Bukkit.getServer().getOnlinePlayers().size() > 1) {
                int numOfPlayers = 0;
                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    p.teleport(player.getLocation());
                    numOfPlayers++;
                }
                player.sendMessage(Messages.PREFIX + ChatColor.GRAY + (numOfPlayers - 1) + " §7joueur(s) ont été téléporté à vous.");
                Bukkit.broadcastMessage(Messages.PREFIX + "§7Tout les joueurs ont été téléporté à§9 " + player.getName());
            }
        }
        return true;
    }
}



