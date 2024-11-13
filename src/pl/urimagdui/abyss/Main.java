package pl.urimagdui.abyss;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pl.urimagdui.abyss.Commands.AbyssCommand;
import pl.urimagdui.abyss.Commands.ClearCommand;
import pl.urimagdui.abyss.Config.Config;
import pl.urimagdui.abyss.Tasks.AbyssTask;
import pl.urimagdui.abyss.Utils.TimeUtil;

public class Main extends JavaPlugin {

    private static Main plugin;

    public static Main getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        new Config();
        getCommand("otchlan").setExecutor(new AbyssCommand());
        getCommand("otchlanadm").setExecutor(new ClearCommand());
        new AbyssTask().runTaskTimer((Plugin) this, TimeUtil.SECOND.getTick(Config.AbyssCooldown), TimeUtil.SECOND.getTick(Config.AbyssCooldown));
        getLogger().info("Plugin sie wlaczyl.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin sie wylaczyl.");
        plugin = null;
    }

}
