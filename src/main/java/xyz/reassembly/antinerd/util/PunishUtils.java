package xyz.reassembly.antinerd.util;

import com.comphenix.protocol.PacketType;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PunishUtils {

    private Plugin plugin;

    public PunishUtils(Plugin plugin) {
        this.plugin = plugin;
    }


    public void banPlayer(Player player, String Reason) {
        // player.setBanned(true);
        player.kickPlayer(ChatColor.translateAlternateColorCodes('&', "&7[&6AntiNerd&7] Caught you cheating \n &7You have been banned from this server for \n\n&6" + Reason));
    }

    public void sendAlert(Player player, String Reason) {
        plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6AntiNerd&7] &6" + player.getName() + " &7flagged &6" + Reason));
    }
}
