package xyz.reassembly.antinerd.checks.movement.noslowdown;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import xyz.reassembly.antinerd.checks.Check;
import xyz.reassembly.antinerd.checks.CheckType;
import xyz.reassembly.antinerd.util.PunishUtils;

import java.util.HashMap;

public class NoSlowDownA extends Check implements Listener {

    private Plugin plugin;
    private PunishUtils punishUtils;
    private HashMap<Player, Integer> NoSlowVL;
    private HashMap<Player, Integer> blockSteps;
    double speedx;
    double speedz;
    double finalyloc;

    public NoSlowDownA(Plugin plugin, PunishUtils punishUtils) {
        super(CheckType.NOSLOWDOWN, "A", plugin);
        this.plugin = plugin;
        this.punishUtils = punishUtils;
        NoSlowVL = new HashMap<>();
        blockSteps = new HashMap<>();
    }

    @EventHandler
    public void on(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.isFlying()) return;
        double speedxfrom = event.getFrom().getX();
        double speedxto = event.getTo().getX();
        double speedx = Math.abs(speedxfrom-speedxto);

        double speedzfrom = event.getFrom().getZ();
        double speedzto = event.getTo().getZ();
        double speedz = Math.abs(speedzfrom-speedzto);

        if (!NoSlowVL.containsKey(player)) NoSlowVL.put(player, 0);
        if (!blockSteps.containsKey(player)) blockSteps.put(player, 0);

        // if (player.isOnGround()) {
            if (player.isBlocking() || player.isSneaking()) {

                double yloc1 = event.getFrom().getY();
                double yloc2 = event.getTo().getY();


                if (yloc1 > yloc2) finalyloc = yloc1 - yloc2;
                if (yloc1 < yloc2) finalyloc = yloc2 - yloc1;

                if (finalyloc < 0.5)

                if (speedx > 0.15 || speedz > 0.15) {

                    NoSlowVL.put(player, NoSlowVL.get(player) + 1);

                    if (NoSlowVL.get(player) > 5) sendAlert(player, NoSlowVL.get(player));

                    if (NoSlowVL.get(player) > 15) banPlayer(player);
                }

            } else {
                blockSteps.put(player, blockSteps.get(player) + 1);
                if (blockSteps.get(player) > 2) {
                    blockSteps.put(player, 0);
                    NoSlowVL.put(player, 0);
                }
            }
        // }
    }
}
