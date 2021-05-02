package xyz.reassembly.antinerd.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import xyz.reassembly.antinerd.AlertsCommand;

public class PunishUtils {

    private Plugin plugin;
    private AlertsCommand commands;

    public PunishUtils(Plugin plugin, AlertsCommand commands) {
        this.plugin = plugin;
        this.commands = commands;
    }


    public void banPlayer(Player player, String Reason) {
        // player.setBanned(true);
        player.kickPlayer(ChatColor.translateAlternateColorCodes('&', "&7[&6AntiNerd&7] Caught you cheating \n &7You have been banned from this server for \n\n&6" + Reason));
    }

    public void sendAlert(Player player, String Reason) {
//        for (Player p : Bukkit.getOnlinePlayers()) {
//            if (commands.getRecieveAlerts().get(p)) p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6AntiNerd&7] &6" + player.getName() + " &7flagged &6" + Reason));
//        }

        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6AntiNerd&7] &6" + player.getName() + " &7flagged &6" + Reason));
    }
}
