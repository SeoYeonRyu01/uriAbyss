package pl.urimagdui.abyss.Tasks;

import org.bukkit.scheduler.BukkitRunnable;
import pl.urimagdui.abyss.Utils.Util;

public class AbyssTask extends BukkitRunnable {

    public void run() {
        Util.clearItems();
    }
}
