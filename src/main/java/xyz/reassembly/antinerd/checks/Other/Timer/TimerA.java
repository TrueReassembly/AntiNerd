package xyz.reassembly.antinerd.checks.Other.Timer;

import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import xyz.reassembly.antinerd.checks.Check;
import xyz.reassembly.antinerd.checks.CheckType;

import java.util.HashMap;

public class TimerA extends Check {

    private Plugin plugin;
    public HashMap<Player, Integer> pps;


    public TimerA(Plugin plugin) {
        super(CheckType.TIMER, "A", plugin);
        this.plugin = plugin;
        pps = new HashMap<>();
        Bukkit.getOnlinePlayers().forEach(player -> {
            pps.put(player, 0);
        });

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                pps.clear();
                Bukkit.getOnlinePlayers().forEach(player -> pps.put(player, 0));
            }
        }, 100, 20);
    }

    public void checkTimerA(PacketContainer packet, Player player) {
        pps.put(player, pps.get(player) + 1);
        player.sendMessage(pps.get(player).toString());
    }
}
