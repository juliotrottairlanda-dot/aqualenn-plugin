
package com.aqualenn.guardian;

import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;

public class MobProtectionListener implements Listener {

    @EventHandler
    public void onTarget(EntityTargetEvent e){

        if(!(e.getTarget() instanceof Player p)) return;
        if(!AqualennGuardianPlugin.isAqualenn(p)) return;

        if(e.getEntity() instanceof Monster){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){

        if(!(e.getEntity() instanceof Player p)) return;
        if(!AqualennGuardianPlugin.isAqualenn(p)) return;

        if(e.getDamager() instanceof Monster ||
           (e.getDamager() instanceof Projectile proj && proj.getShooter() instanceof Monster)){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void piglinTarget(EntityTargetEvent e){
        if(!(e.getTarget() instanceof Player p)) return;
        if(!AqualennGuardianPlugin.isAqualenn(p)) return;

        if(e.getEntity() instanceof Piglin){
            e.setCancelled(true);
        }
    }
}
