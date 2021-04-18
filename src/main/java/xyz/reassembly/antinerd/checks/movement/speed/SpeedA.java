package xyz.reassembly.antinerd.checks.movement.speed;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import xyz.reassembly.antinerd.util.MovementUtils;
import xyz.reassembly.antinerd.util.PunishUtils;

import java.util.HashMap;

public class SpeedA implements Listener {

    private Plugin plugin;
    private PunishUtils punishUtils;
    private MovementUtils movementUtils;
    private HashMap<Player, Integer> SpeedAVL;
    double speedx;
    double speedz;

    public SpeedA(Plugin plugin, PunishUtils punishUtils, MovementUtils movementUtils) {
        this.plugin = plugin;
        this.punishUtils = punishUtils;
        this.movementUtils = movementUtils;
        SpeedAVL = new HashMap<>();
    }

    @EventHandler
    public void on(PlayerMoveEvent event) {
        double speedxfrom = event.getFrom().getX();
        double speedxto = event.getTo().getX();
        if (speedxfrom > speedxto) speedx = speedxfrom - speedxto;
        else if (speedxfrom < speedxto) speedx = speedxto - speedxfrom;

        double speedzfrom = event.getFrom().getX();
        double speedzto = event.getTo().getX();
        if (speedzfrom > speedzto) speedz = speedzfrom - speedzto;
        else if (speedzfrom < speedzto) speedz = speedzto - speedzfrom;

        Player player = event.getPlayer();
        // if (event.getFrom().getY() == event.getTo().getY() || player.getLocation().getDirection() == event.getFrom().getDirection()) {
            if (speedx > 0.58D || speedz > 0.58D ) {
                if (!SpeedAVL.containsKey(player)) SpeedAVL.put(player, 0);

                SpeedAVL.put(player, SpeedAVL.get(player) + 1);

                punishUtils.sendAlert(player, "Speed-A");

                if (SpeedAVL.get(player) > 30) punishUtils.banPlayer(player, "Speed-A");

            }
        //}
    }
}