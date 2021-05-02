package xyz.reassembly.antinerd.checks.movement.noslowdown;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import xyz.reassembly.antinerd.util.PunishUtils;

import java.util.HashMap;

public class NoSlowDownB implements Listener {

    private Plugin plugin;
    PunishUtils punishUtils;
    private HashMap<Player, Integer> NoSlowBVL;


    public NoSlowDownB(Plugin plugin, PunishUtils punishUtils) {
        this.plugin = plugin;
        this.punishUtils = punishUtils;
        NoSlowBVL = new HashMap<>();
    }

    @EventHandler
    public void on(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!NoSlowBVL.containsKey(player)) NoSlowBVL.put(player, 0);

        if (player.isSprinting() && player.isBlocking()) {
            NoSlowBVL.put(player, NoSlowBVL.get(player) + 1);

            if (NoSlowBVL.get(player) > 5) punishUtils.sendAlert(player, "NoSlow [B]");

            if (NoSlowBVL.get(player) > 15) punishUtils.banPlayer(player, "NoSlowDown [B]");
        }
    }

}
