package ua.catfishua.restarter.commands;

import ua.catfishua.restarter.Main;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class Restart implements CommandExecutor {
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player) sender;
            if (!p.isOp()) {
                p.sendMessage(Main.getPlugin(Main.class).getConfig().getString("chat-msgs.no-perm"));
                return true;
            }
            p.sendMessage(Main.getPlugin(Main.class).getConfig().getString("chat-msgs.restart"));
        }
        Main.restartServer();
        return true;
    }
}