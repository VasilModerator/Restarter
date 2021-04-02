package ua.catfishua.restarter;

import org.bukkit.Bukkit;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import ua.catfishua.restarter.commands.Restart;

public final class Main extends JavaPlugin implements Listener {
    Logger log = Logger.getLogger("Minecraft");
    public static JavaPlugin instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        log.info("§e ____             _              _");
        log.info("§e|  _ \\  ___  ___ | |_  __ _ _ __| |_  ___ _ __");
        log.info("§e| |_) |/ _ \\/ __|| __|/ _` | '__| __|/ _ \\ '__|");
        log.info("§6|   _/|  __/\\__ \\| |_| (_| | |  | |_|  __/ |");
        log.info("§6|_|\\_\\ \\___ |___/ \\__|\\__,_|_|   \\__|\\___|_|");
        log.info("                                         §81.0");
        log.info("");
        log.info("By catfishua and Butukay. Original author: DeelTer");
        log.info("");
        log.info("GitHub:");
        log.info("§9https://github.com/catfishua");
        log.info("§9https://github.com/butukay");
		log.info("§9https://github.com/deelter");
        log.info("");
        log.info("Restarter loaded and enabled. Top Notch!");
        log.info("§8______________________________________________________________");

        Main.instance = this;
        if (!new File(String.valueOf(Main.instance.getDataFolder().getPath()) + "/config.yml").exists()) {
            this.saveDefaultConfig();
            this.getLogger().info(this.getConfig().getString("log-msgs.default-config-saved"));
        }

        this.getCommand("autorestart").setExecutor(new Restart());
        this.restartTimer();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveConfig();
    }

    private void restartTimer() {
        if (!(Config.TIME < 0)) {
            Bukkit.getScheduler().runTaskLater(Main.instance, () -> Main.restartServer(), Config.TIME * 20L);
        }
    }

    public static void restartServer() {
        Bukkit.broadcastMessage(Config.getMessage());
        Bukkit.getOnlinePlayers().forEach(p -> p.playSound(p.getLocation(), Config.SOUND, 1.0f, 0.1f));
        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            Bukkit.getOnlinePlayers().forEach(p -> p.kickPlayer(Config.KICK_MESSAGE));
            Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
                Bukkit.getLogger().info(Main.getPlugin(Main.class).getConfig().getString("log-msgs.restart"));
                Bukkit.getServer().shutdown();
            }, 60L);
        }, 200L);
    }

    public static JavaPlugin getInstance() {
        return Main.instance;
    }
}
