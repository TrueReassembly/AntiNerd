package xyz.reassembly.antinerd.checks.combat.AimAssist;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import xyz.reassembly.antinerd.util.MovementUtils;
import xyz.reassembly.antinerd.util.PunishUtils;

import java.util.HashMap;

public class AimassistA implements Listener {

    private Plugin plugin;
    private PunishUtils punishUtils;
    private MovementUtils movementUtils;
    private HashMap<Player, Integer> AimAssistAVL;
    float loc2;

    public AimassistA(Plugin plugin, PunishUtils punishUtils, MovementUtils movementUtils) {
        this.plugin = plugin;
        this.punishUtils = punishUtils;
        this.movementUtils = movementUtils;
        AimAssistAVL = new HashMap<>();
    }

    public void on(EntityDamageByEntityEvent event) {

    }
}
