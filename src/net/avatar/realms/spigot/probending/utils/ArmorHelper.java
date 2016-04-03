package net.avatar.realms.spigot.probending.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.List;

/**
 * Created by Nokorbis on 3/04/2016.
 */
public abstract class ArmorHelper {

    private static final Color RED = Color.RED;
    private static final Color BLUE = Color.AQUA;

    public static ItemStack newRedHelmet() {
        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET, 1);
        setMeta(helmet, RED, Messages.get("probending.armors.helmet"), Messages.get("probending.armors.red"));
        return helmet;
    }

    public static ItemStack newBlueHelmet() {
        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET, 1);
        setMeta(helmet, BLUE, Messages.get("probending.armors.helmet"), Messages.get("probending.armors.blue"));
        return helmet;
    }

    public static ItemStack newRedChestplate() {
        ItemStack helmet = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        setMeta(helmet, RED, Messages.get("probending.armors.chestplate"), Messages.get("probending.armors.red"));
        return helmet;
    }

    public static ItemStack newBlueChestplate() {
        ItemStack helmet = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        setMeta(helmet, RED, Messages.get("probending.armors.chestplate"), Messages.get("probending.armors.blue"));
        return helmet;
    }

    public static ItemStack newRedLeggings() {
        ItemStack helmet = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        setMeta(helmet, RED, Messages.get("probending.armors.leggings"), Messages.get("probending.armors.red"));
        return helmet;
    }

    public static ItemStack newBlueLeggings() {
        ItemStack helmet = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        setMeta(helmet, RED, Messages.get("probending.armors.leggings"), Messages.get("probending.armors.blue"));
        return helmet;
    }

    public static ItemStack newRedBoots() {
        ItemStack helmet = new ItemStack(Material.LEATHER_BOOTS, 1);
        setMeta(helmet, RED, Messages.get("probending.armors.boots"), Messages.get("probending.armors.red"));
        return helmet;
    }

    public static ItemStack newBlueBoots() {
        ItemStack helmet = new ItemStack(Material.LEATHER_BOOTS, 1);
        setMeta(helmet, RED, Messages.get("probending.armors.boots"), Messages.get("probending.armors.blue"));
        return helmet;
    }

    public static boolean isProbendingArmor(ItemStack item) {
        Material type = item.getType();
        if (type != Material.LEATHER_LEGGINGS
                && type != Material.LEATHER_CHESTPLATE
                && type != Material.LEATHER_HELMET
                && type != Material.LEATHER_BOOTS) {
            return false;
        }

        ItemMeta meta = item.getItemMeta();
        String name = meta.getDisplayName();
        if (name != Messages.get("probending.armors.helmet")
                && name != Messages.get("probending.armors.chestplate")
                && name != Messages.get("probending.armors.leggings")
                && name != Messages.get("probending.armors.boots")) {
            return false;
        }

        List<String> lore = meta.getLore();
        if (!lore.contains(Messages.get("probending.armors.red"))
                && !lore.contains(Messages.get("probending.armors.blue"))) {
            return false;
        }
        return true;
    }

    private static final void setMeta(ItemStack armor, Color color, String name, String lore) {
        ItemMeta meta = armor.getItemMeta();
        LeatherArmorMeta lMeta = (LeatherArmorMeta) meta;
        lMeta.setDisplayName(name);
        lMeta.getLore().add(lore);
        lMeta.setColor(color);
        armor.setItemMeta(lMeta);
    }

}
