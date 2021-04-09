package xyz.reassembly.antinerd.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PunishUtils {

    public PunishUtils() {

    }


    public void banPlayer(Player player, String Reason) {
        player.setBanned(true);
        player.kickPlayer(ChatColor.translateAlternateColorCodes('&', "&cYou have been perm banned from this server for \n\n" + Reason));
    }
}
