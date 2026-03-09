
package com.aqualenn.guardian;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AqualennGuardianPlugin extends JavaPlugin {

    public static final String AQUALENN = "Aqualenn";
    public static AqualennGuardianPlugin instance;

    public Set<UUID> horns = new HashSet<>();

    @Override
    public void onEnable() {
        instance = this;

        getServer().getPluginManager().registerEvents(new MobProtectionListener(), this);

        getCommand("horns").setExecutor(new HornCommand(this));
        getCommand("sonicpulse").setExecutor(new AbilityCommands());
        getCommand("wardenshield").setExecutor(new AbilityCommands());
        getCommand("rage").setExecutor(new AbilityCommands());
        getCommand("sculkdash").setExecutor(new AbilityCommands());

        startHornParticles();
    }

    public static boolean isAqualenn(Player p){
        return p.getName().equalsIgnoreCase(AQUALENN);
    }

    private void startHornParticles(){
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for(Player p : Bukkit.getOnlinePlayers()){
                if(!isAqualenn(p)) continue;
                if(!horns.contains(p.getUniqueId())) continue;

                var loc = p.getLocation().add(0,2.2,0);

                p.getWorld().spawnParticle(org.bukkit.Particle.SCULK_SOUL, loc.clone().add(.3,0,0),3);
                p.getWorld().spawnParticle(org.bukkit.Particle.SCULK_SOUL, loc.clone().add(-.3,0,0),3);
                p.getWorld().spawnParticle(org.bukkit.Particle.SOUL_FIRE_FLAME, loc.clone().add(.3,.2,0),2);
                p.getWorld().spawnParticle(org.bukkit.Particle.SOUL_FIRE_FLAME, loc.clone().add(-.3,.2,0),2);
            }
        },0L,5L);
    }
}
