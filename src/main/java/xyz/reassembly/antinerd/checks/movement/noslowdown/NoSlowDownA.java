package xyz.reassembly.antinerd.checks.movement.noslowdown;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import xyz.reassembly.antinerd.util.MovementUtils;
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
        if (!NoSlowVL.containsKey(player)) NoSlowVL.put(player, 0);
        if (!blockSteps.containsKey(player)) blockSteps.put(player, 0);

        if (player.isBlocking() || event.getFrom().getDirection() == player.getLocation().getDirection()) {

            if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {

                if (playerSpeed > 0.1) {

                    NoSlowVL.put(player, NoSlowVL.get(player) + 1);

                    if (NoSlowVL.get(player) > 5) punishUtils.sendAlert(player, "NoSlow-A");

                    if (NoSlowVL.get(player) > 15) punishUtils.banPlayer(player, "NoSlowDown-A");
                }
            }

        } else {
            blockSteps.put(player, blockSteps.get(player) + 1);
            if (blockSteps.get(player) > 2) {
                blockSteps.put(player, 0);
                NoSlowVL.put(player, 0);
            }
        }

    }
}
