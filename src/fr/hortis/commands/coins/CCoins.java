package fr.hortis.commands.coins;

import fr.hortis.Core;
import fr.hortis.utils.PREFIX;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CCoins implements CommandExecutor, ICommand {

    @Override
    public String getCommandName() {
        return "coins";
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                if (!player.hasPermission("core.mod")) {
                    player.sendMessage(PREFIX.SYNTAXE + "§7§o/coins <show|balance>");
                } else {
                    player.sendMessage(PREFIX.SYNTAXE + "§7§o/coins <add> <joueur> <montant>");
                    player.sendMessage(PREFIX.SYNTAXE + "§7§o/coins <show|balance>");
                    player.sendMessage(PREFIX.SYNTAXE + "§7§o/coins <clear> <joueur>");
                }
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("show") || args[0].equalsIgnoreCase("balance")) {
                    player.sendMessage(Messages.PREFIX + "§7Vous avez§e " + (new PlayerManager(Core.manager.getApi().getUserManager().getUser(player.getName()), Core.manager.getApi().getUserManager())).getUserCoins() + " §e⛃");
                } else {
                    if (!player.hasPermission("core.mod")) {
                        player.sendMessage(PREFIX.SYNTAXE + "§7§o/coins <show|balance>");
                    } else {
                        player.sendMessage(PREFIX.SYNTAXE + "§7§o/coins <add> <joueur> <montant>");
                        player.sendMessage(PREFIX.SYNTAXE + "§7§o/coins <show|balance>");
                        player.sendMessage(PREFIX.SYNTAXE + "§7§o/coins <clear> <joueur>");
                    }
                }
            } else if (args.length == 2) {
                if (!player.hasPermission("core.mod")) {
                    player.sendMessage(PREFIX.SYNTAXE + "§9§o/coins <show|balance>");
                } else {
                    if (args[0].equalsIgnoreCase("clear")) {
                        Player targetPlayer = Bukkit.getPlayer(args[1]);
                        if (targetPlayer == null) {
                            player.sendMessage(PREFIX.ERROR + "§cLe joueur n'est pas connecté !");
                        } else {
                            player.sendMessage("§aSUCCES §8» " + "§9" + targetPlayer.getName() + " §7n'a plus de coins.");
                            (new PlayerManager(Core.manager.getApi().getUserManager().getUser(targetPlayer.getPlayer()), Core.manager.getApi().getUserManager())).setUserCoins(0);
                        }
                    } else {
                        if (!player.hasPermission("core.mod")) {
                            player.sendMessage(PREFIX.SYNTAXE + "§9§o/coins <show|balance>");
                        } else {
                            player.sendMessage(PREFIX.SYNTAXE + "§7§o/coins <add> <joueur> <montant>");
                            player.sendMessage(PREFIX.SYNTAXE + "§7§o/coins <show|balance>");
                            player.sendMessage(PREFIX.SYNTAXE + "§7§o/coins <clear> <joueur>");
                        }
                    }
                }
            } else if (args.length == 3) {

                if (!player.hasPermission("core.mod")) {
                    player.sendMessage("§cUsage: /coins <show|balance>");


                } else if (player.hasPermission("core.mod")){
                    Player targetPlayer = Bukkit.getPlayer(args[1]);
                    if (targetPlayer == null) {
                    player.sendMessage("§cCe joueur n'est pas connecté !");
                } else {
                    String message = args[2];

                    if (args[0].equalsIgnoreCase("add")) {
                        player.sendMessage("§aSUCCES §8» " + "" + targetPlayer.getName() + " §7a reçu " + message + " §7coins.");
                        (new PlayerManager(Core.manager.getApi().getUserManager().getUser(targetPlayer.getPlayer()), Core.manager.getApi().getUserManager())).addUserCoins(Integer.parseInt(message));

                    }
                }
            } else {
                    player.sendMessage(PREFIX.SYNTAXE + "§7§o/coins <add> <joueur> <montant>");
                player.sendMessage(PREFIX.SYNTAXE + "§7§o/coins <show|balance>");
                player.sendMessage(PREFIX.SYNTAXE + "§7§o/coins <clear> <joueur>");
            }
        }


        } else {
            sender.sendMessage("§cVous devez être un joueur pour exécuter cette commande !");
        }
        return false;
    }
}

