package xyz.reassembly.antinerd.checks.combat.reach;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import xyz.reassembly.antinerd.checks.Check;
import xyz.reassembly.antinerd.checks.CheckType;
import xyz.reassembly.antinerd.util.AttackUtils;
import xyz.reassembly.antinerd.util.MovementUtils;
import xyz.reassembly.antinerd.util.PunishUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class ReachA extends Check implements Listener {

    public Plugin plugin;
    public AttackUtils attackUtils;
    HashMap<Player, Integer> violationsReach;
    private PunishUtils punishUtils;
    private MovementUtils movementUtils;
    double speed;
    int ping;
    double maxDist = 3;

    public ReachA(Plugin plugin, AttackUtils attackUtils, PunishUtils punishUtils, MovementUtils movementUtils) {
        super(CheckType.REACH, "A", plugin);
        this.plugin = plugin;
        this.attackUtils = attackUtils;
        this.violationsReach = new HashMap<>();
        this.punishUtils = punishUtils;
        this.movementUtils = movementUtils;
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        Entity initdamage = event.getDamager();
        if (initdamage instanceof Player) {

            Entity victim = event.getEntity();
            Player damager = (Player) initdamage;
            double distance = attackUtils.getAttackRange(damager, victim);
            try {
                Object entityPlayer = damager.getClass().getMethod("getHandle").invoke(damager);
                ping = (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
                e.printStackTrace();
            }

            if (!violationsReach.containsKey(damager)) {
                violationsReach.put(damager, 0);
            }

            if (speed == 0 && ping < 333) {
                maxDist = 5D;
            }

            // moving
            else if (speed > 0 && ping < 333)  {
                maxDist = 6D;
            }

            if (distance > 5D) {
                sendAlert(damager, violationsReach.get(damager));
                violationsReach.put(damager, violationsReach.get(damager) + 1);
            }

            if (violationsReach.get(damager) > 10) banPlayer(damager);
        }
    }

    @EventHandler
    public void on(PlayerMoveEvent event) {
        speed = movementUtils.getPlayerSpeed(event);
        // event.getPlayer().sendMessage(String.valueOf(speed));
    }
}
