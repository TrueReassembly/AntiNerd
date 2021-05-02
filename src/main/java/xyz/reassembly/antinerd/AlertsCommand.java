package xyz.reassembly.antinerd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class AlertsCommand implements CommandExecutor {

    private Plugin plugin;
    private HashMap<Player, Boolean> recieveAlerts;

    public AlertsCommand(Plugin plugin, HashMap recieveAlerts) {
        this.plugin = plugin;
        this.recieveAlerts = recieveAlerts;
    }

    public HashMap<Player, Boolean> getRecieveAlerts(){
        return recieveAlerts;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!getRecieveAlerts().containsKey(player)) recieveAlerts.put(player, false);
            if (!getRecieveAlerts().get(player)) {
                recieveAlerts.put(player, true);
                sender.sendMessage(ChatColor.GREEN + "Antinerd Alerts enabled");
            }
            else if (getRecieveAlerts().get(player)) {
                recieveAlerts.put(player, false);
                sender.sendMessage(ChatColor.RED + "Antinerd Alerts disabled");
            }
            else {
                recieveAlerts.put(player, true);
                sender.sendMessage(ChatColor.GREEN + "Antinerd Alerts enabled");
            }

        }
        return true;
    }
}
