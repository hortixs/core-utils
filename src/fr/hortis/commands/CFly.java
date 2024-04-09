package fr.hortis.commands;

import fr.hortis.utils.PREFIX;
import fr.hortis.utils.Pair;
import fr.hortis.utils.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CFly implements CommandExecutor, ICommand {

    @Override
    public String getCommandName() {
        return "fly";
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
        return toggleFly(result.getKey(), result.getValue());
    }

    private boolean toggleFly(Player player, CommandSender sender) {
        player.setAllowFlight(!player.getAllowFlight());
        if (!player.getAllowFlight())
            player.setFlying(false);
        if (sender != null)
            sender.sendMessage(Messages.PREFIX + "§7Le joueur " + ChatColor.BLUE + player.getName() + (player.getAllowFlight() ? " §7peut désormais voler" : " §7ne peut désormais plus voler"));
        player.sendMessage(Messages.PREFIX + ChatColor.GRAY + "Le mode " + ChatColor.BLUE + "fly " + ChatColor.GRAY + "est maintenant " + (player.getAllowFlight() ? "activé." : "désactivé."));
        return true;
    }
}
