package me.nexusnetwork.adventuretools.lightning;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ltRodItem implements CommandExecutor {

    lightningRod ltRod;
    Plugin plugin;

    public ltRodItem(lightningRod ltRod, Plugin plugin) {
        this.ltRod = ltRod;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command lightning, String label, String[] args) {
        if (sender.isOp()) {
            if (args.length <= 0) {
                sender.getServer().getPlayer(sender.getName()).getInventory().addItem(ltRod.getLtRod());
                sender.sendMessage("Gave you one lightning rod!");
            }
            else {
                Player targetPlayer = plugin.getServer().getPlayer(args[0]);
                if (targetPlayer != null) {
                    Location target = targetPlayer.getLocation();
                    String world = targetPlayer.getWorld().getName();
                    plugin.getServer().getWorld(world).strikeLightning(target);
                    sender.sendMessage("Strucked " + targetPlayer.getName());
                }
                else {
                    sender.sendMessage("Error cannot find player!");
                }
            }
        }
        else {
            sender.sendMessage("You do not have permission");
        }
        return true;
    }
}
