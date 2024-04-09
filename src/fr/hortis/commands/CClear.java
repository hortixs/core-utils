package fr.hortis.commands;

import fr.hortis.utils.PREFIX;
import fr.hortis.utils.PlayersManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CClear implements CommandExecutor, TabCompleter, ICommand {

    @Override
    public String getCommandName() {
        return "clear";
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player;

        if (args.length < 1) {

            if (!(sender instanceof Player)) {

                sender.sendMessage(PREFIX.ERROR + "§cVous devez être un joueur pour utiliser cette commande.");
                return true;

            }

            player = (Player) sender;

        } else {

            if (args[0].equalsIgnoreCase("all")) {

                Bukkit.getOnlinePlayers().stream().forEach(p -> PlayersManager.clearPlayer(p));
                Bukkit.broadcastMessage(Messages.PREFIX + "§7Tous les joueurs ont été clear");
                return true;

            }

            player = Bukkit.getPlayer(args[0]);

            if (player == null) {

                sender.sendMessage(PREFIX.ERROR + "§cCe joueur n'est pas connecté.");
                return true;

            }

        }

        PlayersManager.clearPlayer(player);

        if (!player.getName().equalsIgnoreCase(sender.getName()))
            sender.sendMessage(Messages.PREFIX + "§7L'inventaire du joueur " + player.getName() + " a été clear.");

        player.sendMessage(Messages.PREFIX + "§7Votre inventaire a été clear.");
        return true;

    }


    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        List<String> suggestions = new ArrayList<>();

        switch (args.length) {

            case 1:
                Set<String> players = new HashSet<>();

                Bukkit.getOnlinePlayers().stream().forEach(p -> players.add(p.getName()));
                suggestions.addAll(players);
                break;
            default:
                break;

        }

        if (!args[args.length - 1].isEmpty()) {

            suggestions = suggestions.stream().filter(s -> s.toLowerCase().startsWith(args[args.length - 1].toLowerCase())).collect(Collectors.toList());

        }

        return suggestions;

    }
}

