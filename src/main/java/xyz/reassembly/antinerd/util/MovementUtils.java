package xyz.reassembly.antinerd.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class MovementUtils {

    Plugin plugin;

    public MovementUtils(Plugin plugin) {
        this.plugin = plugin;
    }

    public double getPlayerSpeed(PlayerMoveEvent event) {
        Location loc1 = event.getFrom();
        Location loc2 = event.getTo();
        return loc1.distance(loc2);
    }

    public boolean inAir(Player player) {
        ArrayList<Block> blocks = new ArrayList<>();
        Block blocc = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
        Material targetblock = player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType();
        blocks.add(blocc.getRelative(BlockFace.DOWN));
        blocks.add(blocc.getRelative(BlockFace.NORTH));
        blocks.add(blocc.getRelative(BlockFace.SOUTH));
        blocks.add(blocc.getRelative(BlockFace.EAST));
        blocks.add(blocc.getRelative(BlockFace.WEST));
        for (Block block : blocks) {
            if (block.getType() == Material.AIR) continue;

            return false;
        }

        return true;
    }
}
