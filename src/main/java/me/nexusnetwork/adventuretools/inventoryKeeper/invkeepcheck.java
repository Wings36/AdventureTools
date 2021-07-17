package me.nexusnetwork.adventuretools.inventoryKeeper;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

public class invkeepcheck implements @Nullable CommandExecutor {

    invkeepevent invKeepList;
    Plugin plugin;
    public invkeepcheck(Plugin plugin, invkeepevent invKeepList) {
        this.invKeepList = invKeepList;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command gmsg, String label, String[] args) {
        if(sender.isOp()){
            if (args.length <= 0) {
                sender.sendMessage("Error Enter a user");
                return true;
            }
            else {
                if (plugin.getServer().getPlayer(args[0]) != null) {
                    Player player = plugin.getServer().getPlayer(args[0]);
                    sender.sendMessage("Keep Inventory Status for Player: " + player.getName());
                    sender.sendMessage(Boolean.toString(invKeepList.playerCheck(player)));
                    return true;
                }
            }
        }
        else {
            Player player = sender.getServer().getPlayer(sender.getName());
            sender.sendMessage("Keep Inventory Status for Player: " + player.getName());
            sender.sendMessage(Boolean.toString(invKeepList.playerCheck(player)));
            return true;
        }
        return false;
    }
}
