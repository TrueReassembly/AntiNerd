package xyz.reassembly.antinerd.checks;

import jdk.internal.event.Event;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.lang.String;

public abstract class Check implements Listener {

    private final CheckType checkType;
    private final String checkLetter;
    private Plugin plugin;

    public Check(final CheckType checkType, final String checkLetter, final Plugin plugin) {
        this.checkType = checkType;
        this.checkLetter = checkLetter;
        this.plugin = plugin;
    }

    public void sendAlert(Player player, int violations) {
//        for (Player p : Bukkit.getOnlinePlayers()) {
//            if (commands.getRecieveAlerts().get(p)) p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6AntiNerd&7] &6" + player.getName() + " &7flagged &6" + Reason));
//        }

        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6AntiNerd&7] &6" + player.getName() + " &7flagged &6" + this.checkType.toString() + " " + this.checkLetter + " &7[VL: " + violations + "]"));
    }

    public void banPlayer(Player player) {
        // player.setBanned(true);

        if (player == null) return;

        // player.kickPlayer(ChatColor.translateAlternateColorCodes('&', "&7[&6AntiNerd&7] Caught you cheating \n &7You have been banned from this server for \n\n&6" + this.checkType.toString()));
        plugin.getServer().broadcastMessage("");
        plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6ANTINERD&7] caught &6" + player.getName() + " &7cheating."));
        plugin.getServer().broadcastMessage("");
    }
}
