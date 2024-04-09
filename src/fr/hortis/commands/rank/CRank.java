package fr.hortis.commands.rank;


import fr.hortis.utils.PREFIX;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;


public class CRank implements CommandExecutor, ICommand, Listener {


    @Override
    public String getCommandName() {
        return "rank";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(PREFIX.ERROR + "§cSeul un joueur peut executer cette commande.");
            return false;
        }

        Player player = (Player) sender;

        if (label.equalsIgnoreCase("rank")) {

            if (args.length < 1) {
                sender.sendMessage(PREFIX.SYNTAXE + "§9§o/rank <joueur> <groupe>");
                return true;
            }

            if (args.length != 2) {
                sender.sendMessage(PREFIX.SYNTAXE + "§9§o/rank <joueur> <groupe>");
                return false;
            }

            String targetName = args[0];
            Player target = Bukkit.getPlayer(targetName);
            String message = args[1];


            if (Bukkit.getPlayer(targetName) == null) {
                player.sendMessage(PREFIX.ERROR + "§cCe joueur n'est pas connecté.");
                return false;
            }
            player.chat("/lp user " + target.getName() + " parent set " + message);
            player.sendMessage("§aSUCCES §8» " + "" + target.getName() + " §7a reçu le rank : §9" + message);
        }
        return false;
    }
}








