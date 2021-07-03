package me.nexusnetwork.adventuretools.inventoryKeeper;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class invkeepevent implements Listener {

    ArrayList<Player> playerList = new ArrayList<Player>();
    Plugin plugin;

    public invkeepevent(Plugin plugin) {
        this.plugin = plugin;
    }

    public String togglePlayerStatus(Player player) {
        if(playerList.remove(player)) {
            return "Keep Inventory is now off";
        }
        else {
            playerList.add(player);
            return "Keep Inventory is now on";
        }
    }


    //True if enabled
    //False if disabled
    private boolean playerCheck(Player player) { return playerList.contains(player); }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (playerCheck(player)) {
            event.setKeepInventory(true);
            event.setKeepLevel(true);
        }
    }
}
