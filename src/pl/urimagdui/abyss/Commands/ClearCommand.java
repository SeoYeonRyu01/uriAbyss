package pl.urimagdui.abyss.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.urimagdui.abyss.Config.Config;
import pl.urimagdui.abyss.Utils.Util;

public class ClearCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!commandSender.hasPermission(Config.AbyssPermission)) {
            Util.sendMessage(commandSender, Config.AbyssNoPermission);
            return false;
        }

        if (Util.abyssIsPlanned || Util.abyssIsOpen) {
            return Util.sendMessage(commandSender, "&cOtchlan aktualnie jest otwarta lub jest juz zaplanowana");
        }

        Util.clearItems();
        return Util.sendMessage(commandSender, "&aWymusiles start otchlani.");
    }
}
