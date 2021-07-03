package me.nexusnetwork.adventuretools;
import me.nexusnetwork.adventuretools.gmsg.gmsg;
import me.nexusnetwork.adventuretools.inventoryKeeper.inventorykeeper;
import me.nexusnetwork.adventuretools.inventoryKeeper.invkeepevent;
import me.nexusnetwork.adventuretools.lightning.lightningRod;
import me.nexusnetwork.adventuretools.lightning.ltRodItem;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class AdventureTools extends JavaPlugin {

    @Override
    public void onEnable(){
        //Objects
        lightningRod ltRod = new lightningRod(this);
        invkeepevent invKeep = new invkeepevent(this);

        //Commands
        Objects.requireNonNull(this.getCommand("gmsg")).setExecutor(new gmsg(this));
        this.getCommand("lightning").setExecutor(new ltRodItem(ltRod, this));
        this.getCommand("keepinv").setExecutor(new inventorykeeper(this, invKeep));

        //Events
        getServer().getPluginManager().registerEvents(ltRod, this);
        getServer().getPluginManager().registerEvents(invKeep, this);

    }

    @Override
    public void onDisable(){
        //Fired when the server stops and disables all plugins
    }

}
//test
