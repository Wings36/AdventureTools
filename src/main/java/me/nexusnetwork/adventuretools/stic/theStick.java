package me.nexusnetwork.adventuretools.stic;

import me.nexusnetwork.adventuretools.lightning.lightningRod;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class theStick implements CommandExecutor {
    knockbackStic ltRod;
    Plugin plugin;

    public theStick(knockbackStic ltRod, Plugin plugin) {
        this.ltRod = ltRod;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command lightning, String label, String[] args) {
        if (sender.isOp()) {
                sender.getServer().getPlayer(sender.getName()).getInventory().addItem(ltRod.getLtRod());
                sender.sendMessage("Gave you one YEET!");
        }
        else {
            sender.sendMessage("You do not have permission");
        }
        return true;
    }
}
