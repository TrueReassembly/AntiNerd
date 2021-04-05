package xyz.reassembly.antinerd.checks.Combat.Reach;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import xyz.reassembly.antinerd.util.AttackUtils;

import java.awt.*;

public class ReachA implements Listener {

    public Plugin plugin;
    public AttackUtils attackUtils;
    private ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

    public ReachA(Plugin plugin, AttackUtils attackUtils) {
        this.plugin = plugin;
        this.attackUtils = attackUtils;
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        if (damager instanceof Player) {
            Entity victim = event.getEntity();
            double distance = attackUtils.getAttackRange(damager, victim);
            damager.sendMessage(String.valueOf(distance));
            if (distance > 5D) {
                plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6AntiNerd&7] &6" + damager.getName() + " &7flagged &6Reach-A &7Verbose: " + distance));
            }
        }
    }
}
