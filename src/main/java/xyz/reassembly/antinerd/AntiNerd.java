package xyz.reassembly.antinerd;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.reassembly.antinerd.checks.combat.killaura.KillauraA;
import xyz.reassembly.antinerd.checks.combat.reach.ReachA;
import xyz.reassembly.antinerd.checks.movement.flight.FlightA;
import xyz.reassembly.antinerd.checks.movement.noslowdown.NoSlowDownA;
import xyz.reassembly.antinerd.checks.movement.noslowdown.NoSlowDownB;
import xyz.reassembly.antinerd.checks.movement.speed.SpeedA;
import xyz.reassembly.antinerd.listeners.onPlayerJoin;
import xyz.reassembly.antinerd.util.AttackUtils;
import xyz.reassembly.antinerd.util.MovementUtils;
import xyz.reassembly.antinerd.util.PunishUtils;

import java.util.HashMap;

public final class AntiNerd extends JavaPlugin {

    public AttackUtils attackUtils;
    public PunishUtils punishUtils;
    public MovementUtils movementUtils;
    public HashMap<Player, Boolean> recieveAlerts;
    @Override
    public void onEnable() {

        AlertsCommand commands = new AlertsCommand(this, recieveAlerts);
        recieveAlerts = new HashMap<>();
        attackUtils = new AttackUtils(this);
        punishUtils = new PunishUtils(this, commands);
        movementUtils = new MovementUtils(this);
        this.getServer().getPluginManager().registerEvents(new ReachA(this, attackUtils, punishUtils), this);
        this.getServer().getPluginManager().registerEvents(new NoSlowDownA(this, punishUtils), this);
        this.getServer().getPluginManager().registerEvents(new NoSlowDownB(this, punishUtils), this);
        this.getServer().getPluginManager().registerEvents(new SpeedA(this, punishUtils, movementUtils), this);
        this.getServer().getPluginManager().registerEvents(new FlightA(this, punishUtils, movementUtils), this);
        this.getServer().getPluginManager().registerEvents(new KillauraA(this, punishUtils, movementUtils), this);
        this.getServer().getPluginManager().registerEvents(new onPlayerJoin(this, recieveAlerts), this);
        this.getCommand("alerts").setExecutor(new AlertsCommand(this, recieveAlerts));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public HashMap<Player, Boolean> getRecieveAlerts() {
        return recieveAlerts;
    }


}
