package pl.urimagdui.abyss.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import pl.urimagdui.abyss.Config.Config;
import pl.urimagdui.abyss.Main;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Util {

    public static boolean abyssIsOpen = false;
    public static boolean abyssIsPlanned = false;
    public static int items = 0;
    public static List<ItemStack> abyssItemsIn = new ArrayList<ItemStack>();

    public static void clearItems() {
        if (abyssIsOpen || abyssIsPlanned) {
            return;
        }

        for (World world : Bukkit.getServer().getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof Item) {
                    ItemStack s = (((Item) entity).getItemStack());
                    abyssItemsIn.add(((Item) entity).getItemStack());
                    entity.remove();
                    items += s.getAmount();
                }
            }
        }

        if (!abyssItemsIn.isEmpty() || !abyssIsPlanned) {
            abyssIsPlanned = true;
            sendMessage(Bukkit.getOnlinePlayers(), Config.AbyssCleared.replace("{ITEMS}", Integer.toString(items)));
            SortUtil.sortItems();
            startAbyss();
        }
    }

    public static void startAbyss() {
        if (abyssIsOpen) {
            return;
        }

        new BukkitRunnable() {
            public void run() {
                openAbyss();
            }
        }.runTaskLater((Plugin) Main.getPlugin(), TimeUtil.SECOND.getTick(Config.AbyssTimeToOpen));

    }

    public static void openAbyss() {
        if (abyssIsOpen) {
            return;
        }

        abyssIsOpen = true;
        sendMessage(Bukkit.getOnlinePlayers(), Config.AbyssOpened.replace("{SIZE}", Integer.toString(items)));
        new BukkitRunnable() {
            public void run() {
                closeAbyss();
            }
        }.runTaskLater((Plugin) Main.getPlugin(), TimeUtil.SECOND.getTick(Config.AbyssTimeOpened));
    }

    public static void closeAbyss() {
        if (!abyssIsOpen) {
            return;
        }

        for (Player players : Bukkit.getOnlinePlayers()) {
            if (players.getOpenInventory().getTitle().equalsIgnoreCase(Util.fixColor(Config.AbyssName))) {
                players.closeInventory();
            }
        }

        abyssIsOpen = false;
        abyssIsPlanned = false;
        items = 0;
        SortUtil.later.clear();;
        abyssItemsIn.clear();
        sendMessage(Bukkit.getOnlinePlayers(), Config.AbyssClosed);
    }

    public static void openInventory(Player player) {

        if (!abyssIsOpen) {
            Util.sendMessage(player, Config.AbyssIsClosed);
            return;
        }
        player.openInventory(SortUtil.inv);
    }

    public static String fixColor(final String s) {
        if (s == null) {
            return "";
        }
        return ChatColor.translateAlternateColorCodes('&', s.replace("{.}", "•").replace("{>}", "»").replace("{<3}", "♥").replace("{X}", "✖").replace("{Y}", "✔").replace("{:>}", "►").replace("{:<}", "◄").replace("{::>}", "⊳"));
    }

    public static boolean sendMessage(final CommandSender sender, final String message, final String permission) {
        if (sender instanceof ConsoleCommandSender) {
            sendMessage(sender, message);
        }
        return permission != null && permission != "" && sender.hasPermission(permission) && sendMessage(sender, message);
    }

    public static boolean sendMessage(final CommandSender sender, final String message) {
        if (sender instanceof Player) {
            if (message != null || message != "") {
                sender.sendMessage(fixColor(message));
            }
        } else {
            sender.sendMessage(ChatColor.stripColor(fixColor(message)));
        }
        return true;
    }

    public static boolean sendMessage(final Collection<? extends CommandSender> collection, final String message) {
        for (final CommandSender cs : collection) {
            sendMessage(cs, message);
        }
        return true;
    }
}
