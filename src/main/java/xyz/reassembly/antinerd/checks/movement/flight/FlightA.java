package xyz.reassembly.antinerd.checks.movement.flight;

import com.comphenix.protocol.PacketType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import xyz.reassembly.antinerd.checks.Check;
import xyz.reassembly.antinerd.checks.CheckType;
import xyz.reassembly.antinerd.listeners.onPlayerJump;
import xyz.reassembly.antinerd.util.MovementUtils;
import xyz.reassembly.antinerd.util.PunishUtils;

import java.util.HashMap;

public class FlightA extends Check implements Listener {

    private Plugin plugin;
    private PunishUtils punishUtils;
    private MovementUtils movementUtils;
    private HashMap<Player, Integer> FlightAVL;

    public FlightA(Plugin plugin, PunishUtils punishUtils, MovementUtils movementUtils) {
        super(CheckType.FLIGHT, "A", plugin);
        this.plugin = plugin;
        this.punishUtils = punishUtils;
        this.movementUtils = movementUtils;
        FlightAVL = new HashMap<>();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() { FlightAVL.clear(); }
        }, 100, 20);
    }

    @EventHandler
    public void on(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!FlightAVL.containsKey(player)) FlightAVL.put(player, 0);
        if (movementUtils.inAir(player) && !player.isFlying()
        && !movementUtils.isFalling(player) && !onPlayerJump.playerIsJumping.get(player)) {

            FlightAVL.put(player, FlightAVL.get(player) + 1);
            // player.sendMessage(FlightAVL.get(player).toString());
            if (FlightAVL.get(player) > 5) sendAlert(player, FlightAVL.get(player));
            if (FlightAVL.get(player) > 15) banPlayer(player);


        }
    }
}
