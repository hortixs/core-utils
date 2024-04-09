package fr.hortis.commands;

import fr.hortis.utils.PREFIX;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CTp implements CommandExecutor, ICommand {

    @Override
    public String getCommandName() {
        return "tp";
    }


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                player.sendMessage(PREFIX.SYNTAXE + "§9§o/tp <joueur>");
                player.sendMessage(PREFIX.SYNTAXE + "§9§o/tp <joueur> <joueur>");
            } else if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                try {
                    player.teleport(target.getLocation());
                } catch (NullPointerException e) {
                    player.sendMessage(PREFIX.ERROR + "§cCe joueur n'est pas connecté.");
                }
            } else if (args.length == 2) {

                Player playerToSend = Bukkit.getPlayer(args[0]);

                Player target = Bukkit.getPlayer(args[1]);

                try {
                    playerToSend.teleport(target.getLocation());
                } catch (NullPointerException e) {
                    player.sendMessage(PREFIX.ERROR + "§cCe joueur n'est pas connecté.");
                }
            }

        }


        return true;
    }
}


