package me.nexusnetwork.adventuretools;
import me.nexusnetwork.adventuretools.gmsg.gmsg;
import me.nexusnetwork.adventuretools.lightning.lightningRod;
import me.nexusnetwork.adventuretools.lightning.ltRodItem;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class AdventureTools extends JavaPlugin {

    @Override
    public void onEnable(){
        //Objects
        lightningRod ltRod = new lightningRod(this);

        //Commands
        Objects.requireNonNull(this.getCommand("gmsg")).setExecutor(new gmsg(this));
        this.getCommand("lightning").setExecutor(new ltRodItem(ltRod, this));

        //Events
        getServer().getPluginManager().registerEvents(ltRod, this);
    }

    @Override
    public void onDisable(){
        //Fired when the server stops and disables all plugins
    }

}
//test
