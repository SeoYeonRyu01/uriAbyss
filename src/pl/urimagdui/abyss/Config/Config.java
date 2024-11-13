package pl.urimagdui.abyss.Config;

import pl.urimagdui.abyss.Main;

public class Config {

    public static String AbyssName;
    public static String AbyssOpened;
    public static String AbyssClosed;
    public static String AbyssCleared;
    public static String AbyssIsClosed;
    public static String AbyssOnlyPlayer;
    public static String AbyssPermission;
    public static String AbyssNoPermission;
    public static int AbyssTimeOpened;
    public static int AbyssTimeToOpen;
    public static int AbyssCooldown;

    public Config() {
        registerConfig();
    }

    private static void registerConfig() {
        AbyssName = Main.getPlugin().getConfig().getString("Abyss.Name");
        AbyssOpened = Main.getPlugin().getConfig().getString("Abyss.Opened");
        AbyssClosed = Main.getPlugin().getConfig().getString("Abyss.Closed");
        AbyssCleared = Main.getPlugin().getConfig().getString("Abyss.Cleared");
        AbyssIsClosed = Main.getPlugin().getConfig().getString("Abyss.IsClosed");
        AbyssOnlyPlayer = Main.getPlugin().getConfig().getString("Abyss.OnlyPlayer");
        AbyssPermission = Main.getPlugin().getConfig().getString("Abyss.Permission");
        AbyssNoPermission = Main.getPlugin().getConfig().getString("Abyss.NoPermission");

        AbyssTimeOpened = Main.getPlugin().getConfig().getInt("Abyss.TimeOpened");
        AbyssTimeToOpen = Main.getPlugin().getConfig().getInt("Abyss.TimeToOpen");
        AbyssCooldown = Main.getPlugin().getConfig().getInt("Abyss.Cooldown");

    }

}
