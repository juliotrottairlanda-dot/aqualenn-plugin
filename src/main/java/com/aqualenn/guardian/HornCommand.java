
package com.aqualenn.guardian;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class HornCommand implements CommandExecutor {

    private final AqualennGuardianPlugin plugin;

    public HornCommand(AqualennGuardianPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(!(sender instanceof Player p)) return true;

        if(!AqualennGuardianPlugin.isAqualenn(p)){
            p.sendMessage("Only Aqualenn can use this.");
            return true;
        }

        if(plugin.horns.contains(p.getUniqueId())){
            plugin.horns.remove(p.getUniqueId());
            p.sendMessage("Horns disabled");
        }else{
            plugin.horns.add(p.getUniqueId());
            p.sendMessage("Horns enabled");
        }

        return true;
    }
}
