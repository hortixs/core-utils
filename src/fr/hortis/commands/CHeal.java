package fr.hortis.commands;

import fr.hortis.utils.PREFIX;
import fr.hortis.utils.Pair;
import fr.hortis.utils.PlayerUtils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CHeal implements CommandExecutor, ICommand {

    @Override
    public String getCommandName() {
        return "heal";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (sender instanceof Player && !sender.hasPermission("core.mod")) {
            sender.sendMessage("Commande inconnue.");
            return false;
        }

        if (!(sender instanceof Player) && args.length < 1) {
            sender.sendMessage(PREFIX.ERROR + "Vous devez être un joueur pour utiliser cette commande");
            return false;
        }

        Pair<Player, CommandSender> result = PlayerUtils.getTarget(args, sender);
        if (result == null)
            return false;
        return heal(result.getKey(), result.getValue());

    }


    private boolean heal(Player player, CommandSender sender) {

        player.setHealth(player.getMaxHealth());
        if (sender != null) {
            sender.sendMessage(Messages.PREFIX + "§7Tu viens de soigner " + ChatColor.BLUE + player.getName());
            player.sendMessage(Messages.PREFIX + "§7Tu viens de te faire soigner par " + ChatColor.BLUE + sender.getName());
        } else
            player.sendMessage(Messages.PREFIX + "§7Tu viens de te soigner.");
        return true;
    }
}
