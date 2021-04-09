package xyz.reassembly.antinerd.util;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

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

    public List<Block> getBlocksBetweenEntities(Entity p1, Entity p2) {
        Location loc1 = p1.getLocation();
        Location loc2 = p2.getLocation();

        List<Block> blocks = new ArrayList<>();
        int topBlockX = (loc1.getBlockX() < loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());
        int bottomBlockX = (loc1.getBlockX() > loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());

        int topBlockY = (loc1.getBlockY() < loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());
        int bottomBlockY = (loc1.getBlockY() > loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());

        int topBlockZ = (loc1.getBlockZ() < loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());
        int bottomBlockZ = (loc1.getBlockZ() > loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());

        for (int x = bottomBlockX; x <= topBlockX; x++) {
            for (int z = bottomBlockZ; z <= topBlockZ; z++) {
                for (int y = bottomBlockY; y <= topBlockY; y++) {
                    Block block = loc1.getWorld().getBlockAt(x, y, z);

                    if (!block.getType().isOccluding()) continue;

                    blocks.add(block);
                }
            }
        }
        return blocks;
    }
}
