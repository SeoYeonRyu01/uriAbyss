package pl.urimagdui.abyss.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.urimagdui.abyss.Config.Config;
import pl.urimagdui.abyss.Utils.Util;

public class AbyssCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)) {
            Util.sendMessage(commandSender, Config.AbyssOnlyPlayer);
            return false;
        }

        Player player = (Player)commandSender;
        Util.openInventory(player);
        return false;
    }
}
