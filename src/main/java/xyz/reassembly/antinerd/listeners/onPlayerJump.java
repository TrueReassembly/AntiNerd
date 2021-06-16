package xyz.reassembly.antinerd.listeners;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.google.common.collect.Sets;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class onPlayerJump implements Listener {

    public static HashMap<Player, Boolean> playerIsJumping;
    private Plugin plugin;

    public onPlayerJump(Plugin plugin) {
        playerIsJumping = new HashMap<>();
        this.plugin = plugin;
    }


    @EventHandler
    public void onMove(PlayerJumpEvent event) {
        playerIsJumping.put(event.getPlayer(), true);

        Bukkit.getScheduler().runTaskLater(this.plugin, () -> {
            playerIsJumping.put(event.getPlayer(), false);
        }, 10);

    }
}
