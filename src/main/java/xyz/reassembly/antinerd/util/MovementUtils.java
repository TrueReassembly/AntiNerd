package xyz.reassembly.antinerd.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class MovementUtils {

    Plugin plugin;
    private boolean isFalling;

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
        Location point5below = new Location( player.getWorld(), player.getLocation().getX(), player.getLocation().getY() - 0.5, player.getLocation().getZ());
        Block bloccbelowblocc = blocc.getRelative(BlockFace.DOWN);
        blocks.add(point5below.getBlock());
        blocks.add(blocc);
        blocks.add(bloccbelowblocc);
        blocks.add(blocc.getRelative(BlockFace.DOWN));
        blocks.add(blocc.getRelative(BlockFace.NORTH));
        blocks.add(blocc.getRelative(BlockFace.SOUTH));
        blocks.add(blocc.getRelative(BlockFace.EAST));
        blocks.add(blocc.getRelative(BlockFace.WEST));
        blocks.add(blocc.getRelative(BlockFace.NORTH_WEST));
        blocks.add(blocc.getRelative(BlockFace.SOUTH_EAST));
        blocks.add(blocc.getRelative(BlockFace.NORTH_WEST));
        blocks.add(blocc.getRelative(BlockFace.SOUTH_WEST));



        for (Block block : blocks) {
            if (block.getType() == Material.AIR) continue;
            return false;
        }

        return true;
    }

    public double getYSpeed(PlayerMoveEvent event) {
        double fromY = event.getFrom().getY();
        double toY = event.getFrom().getY();
        double finalY = 0;
        if (fromY > toY) finalY = fromY - toY;
        else if (fromY < toY) finalY =  toY - fromY;
        return finalY;
    }

    public String getCardinalDirection(Player player) {
        double rotation = (player.getLocation().getYaw() - 180) % 360;
        if (rotation < 0) {
            rotation += 360.0;
        }
        if (0 <= rotation && rotation < 22.5) {
            return "N";
        } else if (22.5 <= rotation && rotation < 67.5) {
            return "NE";
        } else if (67.5 <= rotation && rotation < 112.5) {
            return "E";
        } else if (112.5 <= rotation && rotation < 157.5) {
            return "SE";
        } else if (157.5 <= rotation && rotation < 202.5) {
            return "S";
        } else if (202.5 <= rotation && rotation < 247.5) {
            return "SW";
        } else if (247.5 <= rotation && rotation < 292.5) {
            return "W";
        } else if (292.5 <= rotation && rotation < 337.5) {
            return "NW";
        } else if (337.5 <= rotation && rotation < 360.0) {
            return "N";
        } else {
            return null;
        }
    }

    public boolean isFalling(Player player) {

        if (player.isFlying()) {

            return false;
        }

        double yFrom = player.getLocation().getY();

        Bukkit.getScheduler().runTaskLater(this.plugin, new Runnable() {

            @Override
            public void run() {

                double yTo = player.getLocation().getY();

                if (yTo < yFrom) {

                    isFalling = true;
                }

                if (yTo >= yFrom) {

                    isFalling = false;
                }
            }
        }, 1);

        return isFalling;
    }
}
