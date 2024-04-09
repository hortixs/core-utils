package fr.hortis.commands;

import fr.hortis.utils.PREFIX;
import fr.hortis.utils.Pair;
import fr.hortis.utils.PlayerUtils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CFeed implements CommandExecutor, ICommand {

    @Override
    public String getCommandName() {
        return "feed";
    }

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
        return feed(result.getKey(), result.getValue());
    }

    private boolean feed(Player player, CommandSender sender) {
        player.setFoodLevel(20);
        player.setSaturation(20.0F);
        if (sender != null) {
            sender.sendMessage(Messages.PREFIX + "§7Tu viens de nourrir§9 " + player.getName());
            player.sendMessage(Messages.PREFIX + "§7Tu viens de faire nourrir par §9 " + player.getName());
        } else
            player.sendMessage(Messages.PREFIX + "§7Tu viens de te nourrir.");
        return true;
    }
}
