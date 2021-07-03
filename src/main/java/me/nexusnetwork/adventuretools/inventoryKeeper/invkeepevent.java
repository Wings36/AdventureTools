package me.nexusnetwork.adventuretools.inventoryKeeper;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class invkeepevent implements Listener {

    ArrayList<Player> playerList = new ArrayList<Player>();
    Plugin plugin;
    DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    public invkeepevent(Plugin plugin) {
        this.plugin = plugin;
    }

    public String togglePlayerStatus(Player player) {
        LocalDateTime now = LocalDateTime.now();
        if(playerList.remove(player)) {
            plugin.getConfig().set("keepinv.player." + player.getName() + ".status", false);
            plugin.getConfig().set("keepinv.player." + player.getName() + ".Last Updated", date.format(now));
            plugin.saveConfig();
            return "Keep Inventory is now off";
        }
        else {
            int counter = 0;
            playerList.add(player);
            plugin.getConfig().set("keepinv.player." + player.getName() + ".status", true);
            if (plugin.getConfig().isInt("keepinv.player." + player.getName() + ".Count")) {
                counter = plugin.getConfig().getInt("keepinv.player." + player.getName() + ".Count", 0);
            }
            plugin.getConfig().set("keepinv.player." + player.getName() + ".Count", ++counter);
            if (!plugin.getConfig().isString("keepinv.player." + player.getName() + ".Initial Start")) {
                plugin.getConfig().set("keepinv.player." + player.getName() + ".Initial Start", date.format(now));
            }
            plugin.getConfig().set("keepinv.player." + player.getName() + ".Last Updated", date.format(now));
            plugin.saveConfig();
            return "Keep Inventory is now on";
        }
    }


    //True if enabled
    //False if disabled
    public boolean playerCheck(Player player) { return playerList.contains(player); }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (plugin.getConfig().getBoolean("keepinv.player." + event.getPlayer().getName() + ".status", false)) {
            playerList.add(event.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (playerCheck(player)) {
            event.setKeepInventory(true);
            event.setKeepLevel(true);
        }
        int counter = 0;
        if (plugin.getConfig().isInt("keepinv.player." + player.getName() + ".KIDeaths")) {
            counter = plugin.getConfig().getInt("keepinv.player." + player.getName() + ".KIDeaths", 0);
        }
        plugin.getConfig().set("keepinv.player." + player.getName() + ".Count", ++counter);
    }
}
