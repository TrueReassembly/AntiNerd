package xyz.reassembly.antinerd.checks.combat.reach;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import xyz.reassembly.antinerd.util.AttackUtils;
import xyz.reassembly.antinerd.util.PunishUtils;

import java.util.HashMap;

public class ReachA implements Listener {

    public Plugin plugin;
    public AttackUtils attackUtils;
    HashMap<Player, Integer> violationsReach;
    private PunishUtils punishUtils;

    public ReachA(Plugin plugin, AttackUtils attackUtils, PunishUtils punishUtils) {
        this.plugin = plugin;
        this.attackUtils = attackUtils;
        this.violationsReach = new HashMap<>();
        this.punishUtils = punishUtils;
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        Entity initdamage = event.getDamager();
        if (initdamage instanceof Player) {
            Entity victim = event.getEntity();
            Player damager = (Player) initdamage;
            double distance = attackUtils.getAttackRange(damager, victim);
            if (!violationsReach.containsKey(damager)) {
                violationsReach.put(damager, 0);
            }

            if (distance > 4.7D) {
                punishUtils.sendAlert(damager, "Reach-A");
                violationsReach.put(damager, violationsReach.get(damager) + 1);
            }

            if (violationsReach.get(damager) > 10) {
                punishUtils.banPlayer(damager, "Reach-A");
            }
        }
    }
}
