package me.nexusnetwork.adventuretools.gmsg;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class gmsg implements CommandExecutor {

    JavaPlugin plugin;

    public gmsg (JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command gmsg, String label, String[] args) {
        if (sender.isOp()) {
            if (args.length >= 2) {
                String message = sendMessage(args);
                if (args[0].equals("@a")) {
                    int playerCount = plugin.getServer().broadcastMessage(message);
                    sender.sendMessage("Sent the following to " + playerCount + " players: " + message);
                    return true;
                }
                else {
                    try {
                        Player target = plugin.getServer().getPlayer(args[0]);
                        target.sendMessage(message);
                        sender.sendMessage("Sending " + target.getDisplayName() + " Message: " + message);
                    }
                    catch (NullPointerException e) {
                        sender.sendMessage("Error player not found!");
                    }
                    return true;


                }
            }
            else
                return false;
        }
        else {
            sender.sendMessage("Error you do not have permission!");
            return true;
        }

    }
    private String sendMessage(String[] args) {
        String message = "";
        for (int x = 1; x < args.length; x++) {
            message += args[x] + " ";
        }
        return message;
    }
}
