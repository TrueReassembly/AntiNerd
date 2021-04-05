package xyz.reassembly.antinerd;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.reassembly.antinerd.checks.Combat.Reach.ReachA;
import xyz.reassembly.antinerd.util.AttackUtils;

public final class AntiNerd extends JavaPlugin {

    private ProtocolManager protocolManager;
    public AttackUtils attackUtils;

    @Override
    public void onEnable() {
        protocolManager = ProtocolLibrary.getProtocolManager();
        attackUtils = new AttackUtils(this);
        this.getServer().getPluginManager().registerEvents(new ReachA(this, attackUtils), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ProtocolManager getProtocolManager() {
        return this.protocolManager;
    }


}
