package xyz.reassembly.antinerd.checks.movement.noslowdown;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import xyz.reassembly.antinerd.util.PunishUtils;

import java.util.HashMap;


public class NoSlowDownA implements Listener {

    private Plugin plugin;
    private PunishUtils punishUtils;
    private HashMap<Player, Integer> NoSlowVL;
    private HashMap<Player, Integer> blockSteps;

    public NoSlowDownA(Plugin plugin, PunishUtils punishUtils) {
        this.plugin = plugin;
        this.punishUtils = punishUtils;
        NoSlowVL = new HashMap<>();
        blockSteps = new HashMap<>();
    }

    @EventHandler
    public void on(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        double playerSpeed = event.getFrom().distance(event.getTo());
        double maxSpeed = 3;
        if (!NoSlowVL.containsKey(player)) NoSlowVL.put(player, 0);
        if (!blockSteps.containsKey(player)) blockSteps.put(player, 0);

        if (player.isBlocking()) {

            if (playerSpeed > 0.087) {

                NoSlowVL.put(player, NoSlowVL.get(player) + 1);

                if (NoSlowVL.get(player) > 5) plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6AntiNerd&7] &6" + player.getName() + " &7flagged &6NoSlow-A &7Verbose: " + playerSpeed + " / " + maxSpeed));

                if (NoSlowVL.get(player) > 15) punishUtils.banPlayer(player, "NoSlowDown-A");
            }

        } else {
            blockSteps.put(player, blockSteps.get(player) + 1);
            if (blockSteps.get(player) > 5) {
                blockSteps.put(player, 0);
                NoSlowVL.put(player, 0);
            }
        }
    }
}
