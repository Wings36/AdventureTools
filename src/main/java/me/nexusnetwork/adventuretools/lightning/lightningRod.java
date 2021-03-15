package me.nexusnetwork.adventuretools.lightning;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class lightningRod implements Listener {

    ItemStack ltRod;
    Plugin plugin;

    public lightningRod (Plugin plugin) {
        this.plugin = plugin;
        ltRod = new ItemStack(Material.STICK);
        setupLtRod();
    }

    @EventHandler
    public void clickItem(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().equals(ltRod)) {
            if (player.isOp()) {
                Location target = event.getPlayer().getTargetBlock(null,300).getLocation();
                String world = player.getWorld().getName();
                plugin.getServer().getWorld(world).strikeLightning(target);
            }
        }
    }
    public ItemStack getLtRod() {
        return ltRod.clone();
    }
    private void setupLtRod() {
        ItemMeta meta = ltRod.getItemMeta();
        meta.setDisplayName("Lightning Rod of the Gods");
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
        meta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ltRod.setAmount(1);
        ltRod.setItemMeta(meta);
    }
}
