package fr.hortis.utils;

import fr.hortis.Core;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class PlayersManager implements Listener {


    public static void clearPlayer(Player player) {

        PlayerInventory inventory = player.getInventory();
        InventoryView openedInventory = player.getOpenInventory();

        inventory.clear();
        inventory.setArmorContents(null);
        player.setItemOnCursor(new ItemStack(Material.AIR));

        if (openedInventory.getType() == InventoryType.CRAFTING) openedInventory.getTopInventory().clear();

        player.setLevel(0);
        player.setTotalExperience(0);
        player.setExp(0f);
        player.setFoodLevel(20);
        player.setSaturation(5f);
        player.setExhaustion(0f);
        player.setHealth(player.getMaxHealth());
        player.getActivePotionEffects().stream().forEach(e -> player.removePotionEffect(e.getType()));

    }


    public static void sendToServer(Player player, String server) throws IOException {

        ByteArrayOutputStream array = new ByteArrayOutputStream();

        try (DataOutputStream output = new DataOutputStream(array)) {

            output.writeUTF("Connect");
            output.writeUTF(server);

        }

        player.sendPluginMessage(Core.manager, "BungeeCord", array.toByteArray());

    }
}
