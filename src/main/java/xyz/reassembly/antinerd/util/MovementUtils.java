package xyz.reassembly.antinerd.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

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

//    public boolean inAir(Player player) {
//        Block blocc = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
//        Location blockloc = blocc.getLocation();
//        Material targetblock = player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType();
//    }
}
