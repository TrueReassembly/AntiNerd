package xyz.reassembly.antinerd.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

import static xyz.reassembly.antinerd.listeners.onPlayerJump.playerIsJumping;

public class onPlayerJoin implements Listener {

    private Plugin plugin;
    private HashMap<Player, Boolean> recieveAlerts;

    public onPlayerJoin(Plugin plugin, HashMap recieveAlerts) {
        this.plugin = plugin;
        this.recieveAlerts = recieveAlerts;
    }

    @EventHandler
    public void on(PlayerJoinEvent event) {
        playerIsJumping.put(event.getPlayer(), false);
        recieveAlerts.put(event.getPlayer(), false);
    }
}
