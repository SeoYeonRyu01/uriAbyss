package pl.urimagdui.abyss.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.urimagdui.abyss.Config.Config;
import java.util.ArrayList;
import java.util.List;

public class SortUtil {

    public static Inventory inv = Bukkit.createInventory(null, 9, Util.fixColor(Config.AbyssName));
    public static List<ItemStack> later = new ArrayList<ItemStack>();

    public static void sortItems() {
        Inventory inventory = Bukkit.createInventory(null, 54, Util.fixColor(Config.AbyssName));
        for (ItemStack s : Util.abyssItemsIn) {
            if (itemPrice(s)) {
                if (inventory.getItem(53) == null || inventory.getItem(53).getType() == Material.AIR) {
                    inventory.addItem(s);
                }
            } else {
                later.add(s);
            }
        }

        for (ItemStack s : later) {
            if (!itemPrice(s)) {
                if (inventory.getItem(53) == null || inventory.getItem(53).getType() == Material.AIR) {
                    inventory.addItem(s);
                }
            }
        }
        inv = inventory;
    }

    public static boolean itemPrice(ItemStack i) {

        return (i.getType() == Material.DIAMOND)
                || (i.getType() == Material.DIAMOND_SWORD)
                || (i.getType() == Material.DIAMOND_AXE)
                || (i.getType() == Material.DIAMOND_HELMET)
                || (i.getType() == Material.DIAMOND_CHESTPLATE)
                || (i.getType() == Material.DIAMOND_LEGGINGS)
                || (i.getType() == Material.DIAMOND_BOOTS)
                || (i.getType() == Material.GOLDEN_APPLE)
                || (i.getType() == Material.DIAMOND_BLOCK)
                || (i.getType() == Material.IRON_BLOCK)
                || (i.getType() == Material.GOLD_BLOCK)
                || (i.getType() == Material.IRON_INGOT)
                || (i.getType() == Material.GOLD_INGOT)
                || (i.getType() == Material.OBSIDIAN)
                || (i.getType() == Material.SAND);
    }
}
