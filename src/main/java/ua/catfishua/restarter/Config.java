package ua.catfishua.restarter;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.Sound;

public class Config {
    public static String KICK_MESSAGE;
    public static Sound SOUND;
    public static long TIME;

    static {
        Config.KICK_MESSAGE = color(config().getString("kick-message"));
        Config.SOUND = Sound.valueOf(config().getString("sound"));
        Config.TIME = config().getInt("time") * 60L;

    }

    public static String getMessage() {
        final StringBuilder help = new StringBuilder();
        for (final String s : config().getStringList("restart-message")) {
            help.append(String.valueOf(color(s)) + "\n");
        }
        return help.toString();
    }

    private static FileConfiguration config() {
        return Main.getInstance().getConfig();
    }

    private static String color(final String text) {
        return ChatColor.translateAlternateColorCodes('§', text);
    }
}
