package fr.hortis.commands;


import fr.hortis.utils.Pair;
import fr.hortis.utils.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class CPing implements CommandExecutor, ICommand {

    @Override
    public String getCommandName() {
        return "ping";
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) && args.length < 1) {
            sender.sendMessage(Messages.PREFIX + "Vous devez être un joueur pour utiliser cette commande");
            return false;
        }

        Pair<Player, CommandSender> result = PlayerUtils.getTarget(args, sender);
        if (result == null)
            return false;
        return ping(result.getKey(), result.getValue());
    }

    private boolean ping(Player player, CommandSender sender) {
        int ping = ((CraftPlayer) player).getHandle().ping;
        if (sender != null)
            sender.sendMessage(Messages.PREFIX + ChatColor.GRAY + "Le ping de " + ChatColor.BLUE + player.getName() + ChatColor.GRAY + " est de: " + ChatColor.GREEN + ping + " ms");
        else
            player.sendMessage(Messages.PREFIX + ChatColor.GRAY + "§7Votre ping est de: " + ChatColor.GREEN + ping + " ms");
        return true;
    }
}
