package xyz.reassembly.antinerd;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.reassembly.antinerd.checks.Other.Timer.TimerA;
import xyz.reassembly.antinerd.checks.combat.killaura.KillauraA;
import xyz.reassembly.antinerd.checks.combat.reach.ReachA;
import xyz.reassembly.antinerd.checks.movement.flight.FlightA;
import xyz.reassembly.antinerd.checks.movement.noslowdown.NoSlowDownA;
import xyz.reassembly.antinerd.checks.movement.noslowdown.NoSlowDownB;
import xyz.reassembly.antinerd.checks.movement.speed.SpeedA;
import xyz.reassembly.antinerd.listeners.onPlayerJoin;
import xyz.reassembly.antinerd.listeners.onPlayerJump;
import xyz.reassembly.antinerd.util.AttackUtils;
import xyz.reassembly.antinerd.util.MovementUtils;
import xyz.reassembly.antinerd.util.PunishUtils;

import java.util.HashMap;
import java.util.Timer;

public final class AntiNerd extends JavaPlugin {

    public AttackUtils attackUtils;
    public PunishUtils punishUtils;
    public MovementUtils movementUtils;
    public HashMap<Player, Boolean> recieveAlerts;
    public TimerA timerA;

    ProtocolManager manager = ProtocolLibrary.getProtocolManager();

    @Override
    public void onEnable() {



        AlertsCommand commands = new AlertsCommand(this, recieveAlerts);
        recieveAlerts = new HashMap<>();
        attackUtils = new AttackUtils(this);
        punishUtils = new PunishUtils(this, commands);
        movementUtils = new MovementUtils(this);

        timerA = new TimerA(this);


        manager.addPacketListener(new PacketAdapter(this, ListenerPriority.NORMAL, PacketType.Play.Client.ABILITIES) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                timerA.checkTimerA(event.getPacket(), event.getPlayer());
            }
        });

        this.getServer().getPluginManager().registerEvents(new ReachA(this, attackUtils, punishUtils, movementUtils), this);
        this.getServer().getPluginManager().registerEvents(new NoSlowDownA(this, punishUtils), this);
        this.getServer().getPluginManager().registerEvents(new NoSlowDownB(this, punishUtils), this);
        this.getServer().getPluginManager().registerEvents(new SpeedA(this, punishUtils, movementUtils), this);
        this.getServer().getPluginManager().registerEvents(new FlightA(this, punishUtils, movementUtils), this);
        this.getServer().getPluginManager().registerEvents(new KillauraA(this, punishUtils, movementUtils), this);
        this.getServer().getPluginManager().registerEvents(new onPlayerJoin(this, recieveAlerts), this);
        this.getServer().getPluginManager().registerEvents(new onPlayerJump(this), this);
        this.getServer().getPluginManager().registerEvents(timerA, this);
        this.getCommand("alerts").setExecutor(new AlertsCommand(this, recieveAlerts));

        onPlayerJump.playerIsJumping.clear();
        Bukkit.getOnlinePlayers().forEach(player -> {
            onPlayerJump.playerIsJumping.put(player, false);
        });


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public HashMap<Player, Boolean> getRecieveAlerts() {
        return recieveAlerts;
    }


    }


