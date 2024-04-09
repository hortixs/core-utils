package fr.hortis.commands;

import fr.hortis.utils.PREFIX;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class CHead implements CommandExecutor, ICommand {


    @Override
    public String getCommandName() {
        return "head";
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("core.mod") && sender instanceof Player) {
            if (args.length == 1 && args[0].length() >= 32) {
                ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                SkullMeta skull = (SkullMeta) item.getItemMeta();
                skull.setDisplayName("head");
                skull.setOwner(args[0]);
                item.setItemMeta(skull);
                Player player = (Player) sender;
                player.getInventory().addItem(item);
            } else if (args.length == 1) {
                ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                SkullMeta skull = (SkullMeta) item.getItemMeta();
                skull.setDisplayName(args[0]);
                skull.setOwner(args[0]);
                item.setItemMeta(skull);
                Player player = (Player) sender;
                player.getInventory().addItem(item);
            } else {
                sender.sendMessage(PREFIX.SYNTAXE + "§9§o/head <pseudo>");
            }
        } else {
            sender.sendMessage("Commande inconnue.");
        }
        return true;
    }
}
