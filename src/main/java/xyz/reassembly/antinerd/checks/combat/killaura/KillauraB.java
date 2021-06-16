package xyz.reassembly.antinerd.checks.combat.killaura;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.reassembly.antinerd.checks.Check;
import xyz.reassembly.antinerd.checks.CheckType;
import xyz.reassembly.antinerd.util.MovementUtils;
import xyz.reassembly.antinerd.util.PunishUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class KillauraB extends Check implements Listener {

    private Plugin plugin;
    private PunishUtils punishUtils;
    private MovementUtils movementUtils;
    private HashMap<Player, Integer> KillauraBVL;
    private HashMap<Player, ArrayList<Entity>> entitiespersec;
    ArrayList<Entity> entityArrayList;

    public KillauraB(Plugin plugin, PunishUtils punishUtils, MovementUtils movementUtils) {
        super(CheckType.KILLAURA, "B", plugin);
        this.plugin = plugin;
        this.punishUtils = punishUtils;
        this.movementUtils = movementUtils;
        KillauraBVL = new HashMap<>();
        entitiespersec = new HashMap<>();
        entityArrayList = new ArrayList<>();

        new BukkitRunnable() {

            @Override
            public void run() {
                entitiespersec.clear();
                Bukkit.getOnlinePlayers().forEach(player -> entitiespersec.put(player, null));
            }
        }.runTaskTimer(plugin, 20, 20);
    }


    @EventHandler
    public void on(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player attacker = (Player) event.getDamager();
            if (!KillauraBVL.containsKey(attacker)) KillauraBVL.put(attacker, 0);
            if (!entitiespersec.containsKey(attacker)) entitiespersec.put(attacker, null);
            if (entityArrayList.contains(event.getEntity())) {
                entityArrayList.add(event.getEntity());
                entitiespersec.put(attacker, entityArrayList);
            }
            if (entitiespersec.get(attacker).size() > 3) {
                KillauraBVL.put(attacker, KillauraBVL.get(attacker) + 1);

                if (KillauraBVL.get(attacker) > 5) sendAlert(attacker, KillauraBVL.get(attacker));

                if (KillauraBVL.get(attacker) > 15) banPlayer(attacker);


            }
        }
    }
}
