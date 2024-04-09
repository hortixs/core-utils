package fr.hortis.commands;

import fr.hortis.utils.PREFIX;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class CGm implements CommandExecutor, ICommand, TabCompleter {

    @Override
    public String getCommandName() {
        return "gamemode";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player;

        if (args.length < 1) {

            sender.sendMessage(PREFIX.SYNTAXE + "§7§o/gamemode <joueur> <gamemode>");
            return true;

        }

        GameMode gamemode;

        try {

            int number = Integer.valueOf(args[0]);

            if (number == 0) number = 1;
            else if (number == 1) number = 0;

            gamemode = GameMode.values()[number];

        } catch (NumberFormatException exception) {

            try {

                gamemode = GameMode.valueOf(args[0].toUpperCase());

            } catch (IllegalArgumentException exception2) {

                sender.sendMessage(PREFIX.ERROR + "§cCe type de gamemode n'est pas disponible.");
                return true;

            }

        }

        if (gamemode == null) {

            sender.sendMessage("§fCommande inconnue.");
            return true;

        }

        if (args.length < 2) {

            if (!(sender instanceof Player)) {

                sender.sendMessage(Messages.PREFIX + "§cVous devez être un joueur pour utiliser cette commande.");
                return true;

            }

            player = (Player) sender;

        } else {

            player = Bukkit.getPlayer(args[1]);

            if (player == null) {

                sender.sendMessage(Messages.PREFIX + "§cCe joueur n'est pas connect?.");
                return true;

            }

        }

        player.setGameMode(gamemode);

        if (!player.getName().equalsIgnoreCase(sender.getName()))
            sender.sendMessage(Messages.PREFIX + "§7Le mode de jeu du joueur " + player.getName() + "a été règlé sur " + gamemode.name().toLowerCase() + "§7.");

        player.sendMessage(Messages.PREFIX + "§7Vous êtes maintenant en " + ChatColor.AQUA + gamemode.name().toLowerCase());
        return true;

    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        List<String> suggestions = new ArrayList<>();

        switch (args.length) {

            case 1:
                Set<String> gamemodes = new HashSet<>();

                Arrays.stream(GameMode.values()).forEach(g -> gamemodes.add(g.name().toLowerCase()));
                suggestions.addAll(gamemodes);
                break;
            case 2:
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
