package me.nexusnetwork.adventuretools.compass;

import org.apache.commons.lang.ObjectUtils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class compassTuner implements CommandExecutor {

    Plugin plugin;

    // /compass {set/lookup/cancel}
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command compass, @NotNull String label, @NotNull String[] args) {
        if (sender.isOp()) {
            if (args.length == 5) {
                //Set command
                if (args[0].toLowerCase().equals("set")) {
                    try {
                        Player target = plugin.getServer().getPlayer(args[1]);
                        Location destination = new Location(target.getWorld(), Double.parseDouble(args[2]), Double.parseDouble(args[3]), Double.parseDouble(args[4]));
                        target.setCompassTarget(destination);
                    } catch (NullPointerException e) {
                        return false;
                    }

                }
                //Look up
                if (args[0].toLowerCase().equals("lookup")) {
                    try {
                        Player target = plugin.getServer().getPlayer(args[1]);
                        Location point = target.getLocation();
                        sender.sendMessage(target.getName() + " is currently on world " + point.getWorld().getName() +
                                "\nat coords X: " + point.getX() + " Y: " + point.getY() + " Z: " + point.getZ());
                    } catch (NullPointerException e) {
                        return false;
                    }

                }
            }
            else
                return false;
        }
        else {
            sender.sendMessage("You do not have permission!");
        }
        return true;
    }
}
