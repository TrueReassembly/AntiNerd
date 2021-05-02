package xyz.reassembly.antinerd.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class onPlayerJoin implements Listener {

    private Plugin plugin;
    private HashMap<Player, Boolean> recieveAlerts;

    public onPlayerJoin(Plugin plugin, HashMap recieveAlerts) {
        this.plugin = plugin;
        this.recieveAlerts = recieveAlerts;
    }

    public void on(PlayerJoinEvent event) {
        recieveAlerts.put(event.getPlayer(), false);
    }
}
