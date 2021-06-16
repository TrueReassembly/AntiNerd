package xyz.reassembly.antinerd.checks.movement.speed;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import xyz.reassembly.antinerd.checks.Check;
import xyz.reassembly.antinerd.checks.CheckType;
import xyz.reassembly.antinerd.util.MovementUtils;
import xyz.reassembly.antinerd.util.PunishUtils;

import java.util.HashMap;

public class SpeedA extends Check implements Listener {

    private Plugin plugin;
    private PunishUtils punishUtils;
    private MovementUtils movementUtils;
    private HashMap<Player, Integer> SpeedAVL;
    double speedx;
    double speedz;

    public SpeedA(Plugin plugin, PunishUtils punishUtils, MovementUtils movementUtils) {
        super(CheckType.SPEED, "A", plugin);
        this.plugin = plugin;
        this.punishUtils = punishUtils;
        this.movementUtils = movementUtils;
        SpeedAVL = new HashMap<>();
    }

    @EventHandler
    public void on(PlayerMoveEvent event) {

        if (event.getFrom().getYaw() != event.getTo().getYaw()) return;
        if (event.getPlayer().isFlying()) return;
        double speedxfrom = event.getFrom().getX();
        double speedxto = event.getTo().getX();
        if (speedxfrom == speedxto) return;
        if (speedxfrom > speedxto) speedx = speedxfrom - speedxto;
        else if (speedxfrom < speedxto) speedx = speedxto - speedxfrom;

        double speedzfrom = event.getFrom().getZ();
        double speedzto = event.getTo().getZ();
        if (speedzfrom > speedzto) speedz = speedzfrom - speedzto;
        else if (speedzfrom < speedzto) speedz = speedzto - speedzfrom;

        Player player = event.getPlayer();
        // if (event.getFrom().getY() == event.getTo().getY() || player.getLocation().getDirection() == event.getFrom().getDirection()) {
            if (speedx > 0.7D || speedz > 0.7D ) {
                if (!SpeedAVL.containsKey(player)) SpeedAVL.put(player, 0);

                SpeedAVL.put(player, SpeedAVL.get(player) + 1);

                sendAlert(player, SpeedAVL.get(player));

                if (SpeedAVL.get(player) > 10) banPlayer(player);

            }
        //}
    }
}