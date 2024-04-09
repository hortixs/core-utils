package fr.hortis.commands;

import fr.hortis.utils.PREFIX;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CPvp implements CommandExecutor, ICommand {

    @Override
    public String getCommandName() {
        return "pvp";
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && !sender.hasPermission("core.mod")) {
            sender.sendMessage("Commande inconnue.");
            return false;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(PREFIX.ERROR + "Vous devez être un joueur pour utiliser cette commande");
            return false;
        }

        Player player = (Player) sender;

        if (player.getWorld().getPVP())
            player.sendMessage(Messages.PREFIX + ChatColor.RED + "PvP désactivé");
        else
            player.sendMessage(Messages.PREFIX + ChatColor.GREEN + "PvP activé");

        player.getWorld().setPVP(!player.getWorld().getPVP());

        return true;
    }
}
