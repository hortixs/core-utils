package fr.hortis.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class PlayerUtils {

    public static Pair<Player, CommandSender> getTarget(String[] args, CommandSender sender) {
        if (args.length < 1)
            return new Pair<>((Player) sender, null);

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage(Messages.PREFIX + "Ce joueur n'est pas connecté.");
            return null;
        }

        return new Pair<>(target, sender);
    }
}
