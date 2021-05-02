package xyz.reassembly.antinerd.checks.movement.noslowdown;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import xyz.reassembly.antinerd.util.PunishUtils;

import java.util.HashMap;
import java.util.Locale;


public class NoSlowDownA implements Listener {

    private Plugin plugin;
    private PunishUtils punishUtils;
    private HashMap<Player, Integer> NoSlowVL;
    private HashMap<Player, Integer> blockSteps;
    double speedx;
    double speedz;
    double finalyloc;

    public NoSlowDownA(Plugin plugin, PunishUtils punishUtils) {
        this.plugin = plugin;
        this.punishUtils = punishUtils;
        NoSlowVL = new HashMap<>();
        blockSteps = new HashMap<>();
    }

    @EventHandler
    public void on(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        double speedxfrom = event.getFrom().getX();
        double speedxto = event.getTo().getX();
        if (speedxfrom > speedxto) speedx = speedxfrom - speedxto;
        else if (speedxfrom < speedxto) speedx = speedxto - speedxfrom;

        double speedzfrom = event.getFrom().getX();
        double speedzto = event.getTo().getX();
        if (speedzfrom > speedzto) speedz = speedzfrom - speedzto;
        else if (speedzfrom < speedzto) speedz = speedzto - speedzfrom;
        if (!NoSlowVL.containsKey(player)) NoSlowVL.put(player, 0);
        if (!blockSteps.containsKey(player)) blockSteps.put(player, 0);

        // if (player.isOnGround()) {
            if (player.isBlocking()) {

                double yloc1 = event.getFrom().getY();
                double yloc2 = event.getTo().getY();


                if (yloc1 > yloc2) finalyloc = yloc1 - yloc2;
                if (yloc1 < yloc2) finalyloc = yloc2 - yloc1;

                if (finalyloc < 0.5)

                if (speedx > 0.125 || speedz > 0.125) {

                    NoSlowVL.put(player, NoSlowVL.get(player) + 1);

                    if (NoSlowVL.get(player) > 5) punishUtils.sendAlert(player, "NoSlow [A]");

                    if (NoSlowVL.get(player) > 15) punishUtils.banPlayer(player, "NoSlowDown [A]");
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
