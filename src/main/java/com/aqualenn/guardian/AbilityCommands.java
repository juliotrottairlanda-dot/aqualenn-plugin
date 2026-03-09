
package com.aqualenn.guardian;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.potion.*;
import org.bukkit.util.Vector;

public class AbilityCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if(!(sender instanceof Player p)) return true;

        if(!AqualennGuardianPlugin.isAqualenn(p)){
            p.sendMessage("Only Aqualenn can use this.");
            return true;
        }

        switch(command.getName().toLowerCase()){

            case "sonicpulse":
                sonicPulse(p);
                break;

            case "wardenshield":
                wardenShield(p);
                break;

            case "rage":
                rage(p);
                break;

            case "sculkdash":
                sculkDash(p);
                break;
        }

        return true;
    }

    private void sonicPulse(Player p){

        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WARDEN_SONIC_BOOM, 3, 1);

        for(Entity e : p.getNearbyEntities(25,25,25)){
            if(e instanceof LivingEntity le && e != p){

                le.damage(12,p);

                Vector knock = le.getLocation().toVector().subtract(p.getLocation().toVector()).normalize().multiply(1.5);
                le.setVelocity(knock);

                le.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS,120,0));
            }
        }

        p.getWorld().spawnParticle(Particle.SCULK_SOUL, p.getLocation(), 200,3,1,3,.1);
    }

    private void wardenShield(Player p){

        p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE,120,4));
        p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION,120,4));

        p.getWorld().spawnParticle(Particle.SCULK_CHARGE_POP, p.getLocation(), 150,1,1,1,.2);
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WARDEN_ROAR,2,1);
    }

    private void rage(Player p){

        p.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH,200,2));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,200,1));
        p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE,200,1));

        for(Entity e : p.getNearbyEntities(12,12,12)){
            if(e instanceof LivingEntity le && e != p){
                le.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS,200,0));
            }
        }

        p.getWorld().spawnParticle(Particle.SCULK_SOUL, p.getLocation(), 300,2,2,2,.3);
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WARDEN_ROAR,3,0.8f);
    }

    private void sculkDash(Player p){

        Vector direction = p.getLocation().getDirection().normalize();
        p.setVelocity(direction.multiply(2.5));

        p.getWorld().spawnParticle(Particle.SCULK_CHARGE, p.getLocation(), 100,.5,.5,.5,.1);
        p.getWorld().playSound(p.getLocation(), Sound.BLOCK_SCULK_SHRIEKER_SHRIEK,1,1);
    }
}
