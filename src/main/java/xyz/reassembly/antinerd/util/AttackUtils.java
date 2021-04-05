package xyz.reassembly.antinerd.util;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class AttackUtils {

    Plugin plugin;

    public AttackUtils(Plugin plugin) {
        this.plugin = plugin;
    }

    public double getAttackRange(Entity p1, Entity p2) {
        Location p1l = p1.getLocation();
        Location p2l = p2.getLocation();
        return p1l.distance(p2l);

    }
}
