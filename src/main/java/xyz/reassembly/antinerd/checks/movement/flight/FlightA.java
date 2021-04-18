package xyz.reassembly.antinerd.checks.movement.flight;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import xyz.reassembly.antinerd.util.MovementUtils;
import xyz.reassembly.antinerd.util.PunishUtils;

import java.util.HashMap;

public class FlightA implements Listener {

    private Plugin plugin;
    private PunishUtils punishUtils;
    private MovementUtils movementUtils;
    private HashMap<Player, Integer> FlightAVL;

    public FlightA(Plugin plugin, PunishUtils punishUtils, MovementUtils movementUtils) {
        this.plugin = plugin;
        this.punishUtils = punishUtils;
        this.movementUtils = movementUtils;
        FlightAVL = new HashMap<>();
    }

    @EventHandler
    public void on(PlayerMoveEvent event) {

    }
}
