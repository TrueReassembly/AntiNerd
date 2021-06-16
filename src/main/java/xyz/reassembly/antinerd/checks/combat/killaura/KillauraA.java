package xyz.reassembly.antinerd.checks.combat.killaura;

import com.comphenix.protocol.PacketType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import xyz.reassembly.antinerd.checks.Check;
import xyz.reassembly.antinerd.checks.CheckType;
import xyz.reassembly.antinerd.util.MovementUtils;
import xyz.reassembly.antinerd.util.PunishUtils;

import java.util.HashMap;

public class KillauraA extends Check implements Listener {

    private Plugin plugin;
    private PunishUtils punishUtils;
    private MovementUtils movementUtils;
    private HashMap<Player, Integer> KillauraAVL;
    private HashMap<Player, Integer> clicks;

    public KillauraA(Plugin plugin, PunishUtils punishUtils, MovementUtils movementUtils) {
        super(CheckType.KILLAURA, "A", plugin);
        this.plugin = plugin;
        this.punishUtils = punishUtils;
        this.movementUtils = movementUtils;
        KillauraAVL = new HashMap<>();
        clicks = new HashMap<>();

        new BukkitRunnable() {

            @Override
            public void run() {
                clicks.clear();
                Bukkit.getOnlinePlayers().forEach(player -> clicks.put(player, 0));
            }
        }.runTaskTimer(plugin, 100, 100);
    }


    @EventHandler
    public void on(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player attacker = (Player) event.getDamager();
            if (!KillauraAVL.containsKey(attacker)) KillauraAVL.put(attacker, 0);
            if (!clicks.containsKey(attacker)) clicks.put(attacker, 0);
            clicks.put(attacker, clicks.get(attacker) + 1);
            attacker.sendMessage(String.valueOf(clicks.get(attacker)));
            if (clicks.get(attacker) > 13) {
                KillauraAVL.put(attacker, KillauraAVL.get(attacker) + 1);

                if (KillauraAVL.get(attacker) > 3) sendAlert(attacker, KillauraAVL.get(attacker));

                if (KillauraAVL.get(attacker) > 10) banPlayer(attacker);


            }
        }
    }
}
