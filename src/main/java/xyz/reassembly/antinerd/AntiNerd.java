package xyz.reassembly.antinerd;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.reassembly.antinerd.checks.combat.reach.ReachA;
import xyz.reassembly.antinerd.checks.movement.noslowdown.NoSlowDownA;
import xyz.reassembly.antinerd.util.AttackUtils;
import xyz.reassembly.antinerd.util.PunishUtils;

public final class AntiNerd extends JavaPlugin {

    public AttackUtils attackUtils;
    public PunishUtils punishUtils;

    @Override
    public void onEnable() {
        
        attackUtils = new AttackUtils(this);
        punishUtils = new PunishUtils();
        this.getServer().getPluginManager().registerEvents(new ReachA(this, attackUtils, punishUtils), this);
        this.getServer().getPluginManager().registerEvents(new NoSlowDownA(this, punishUtils), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
