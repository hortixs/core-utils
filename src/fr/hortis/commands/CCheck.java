package fr.hortis.commands;

import fr.hortis.utils.PREFIX;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CCheck implements CommandExecutor, ICommand {

    @Override
    public String getCommandName() {
        return "check";
    }


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                player.sendMessage(PREFIX.SYNTAXE + "§9§o/check <joueur>");
            } else if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                UUID uuid = target.getUniqueId();
                try {
                    player.sendMessage("§7Informations §8• §9" + target.getName());
                    player.sendMessage("");
                    player.sendMessage("§8» §7Pseudo:§9 " + target.getName());
                    player.sendMessage("§8» §7UUID:§9 " + uuid);
                } catch (NullPointerException e) {
                    player.sendMessage(PREFIX.ERROR + "§cCe joueur n'est pas connecté.");
                }

            }
        }
        return true;
    }
}



