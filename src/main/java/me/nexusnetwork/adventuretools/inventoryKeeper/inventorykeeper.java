package me.nexusnetwork.adventuretools.inventoryKeeper;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class inventorykeeper implements @Nullable CommandExecutor {

    Plugin plugin;
    invkeepevent invkeep;

    public inventorykeeper(Plugin plugin, invkeepevent invkeep) {
        this.plugin = plugin;
        this.invkeep = invkeep;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command gmsg, String label, String[] args) {
        sender.sendMessage(invkeep.togglePlayerStatus(plugin.getServer().getPlayer(sender.getName())));
        return true;
    }
}
