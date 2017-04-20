/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gwenzy.fallenkingdoms.events;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import fr.gwenzy.fallenkingdoms.Main;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 *
 * @author gwend
 */
public class BlockPlaceListener implements Listener{
    
    @EventHandler
    public void onBlockPlayer(BlockPlaceEvent event){
        //if(Main.g.checkIsInitialized())
            if(Main.g.getTeamRegion(Main.g.getTeam(event.getPlayer().getUniqueId())).contains(event.getBlock().getX(), event.getBlock().getY(), event.getBlock().getZ())){
                return;
            }
            else{
                ProtectedRegion regionCyan = Main.g.getTeamRegion("cyan");
                ProtectedRegion regionYellow = Main.g.getTeamRegion("yellow");
                ProtectedRegion regionRed = Main.g.getTeamRegion("red");
                
                switch("team_"+Main.g.getTeam(event.getPlayer().getUniqueId())){
                    case "team_cyan":
                        if(regionYellow.contains(event.getBlock().getX(), event.getBlock().getY(), event.getBlock().getZ())||regionRed.contains(event.getBlock().getX(), event.getBlock().getY(), event.getBlock().getZ())){
                            if(!Main.allowedBlockAdvBase.contains(event.getBlock().getType())){
                            event.setCancelled(true);
                            event.getPlayer().sendMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.RED+"Vous ne pouvez pas poser ça ici");
                            }
                            else{
                                if(Main.g.getActualDay()<Main.g.getDayAssaut())
                                {
                                    
                            event.setCancelled(true);
                            event.getPlayer().sendMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.RED+"Vous ne pouvez pas poser ça ici maintenant.");
                            }}
                            
                            
                                    
                        }else{
                            
                            if(event.getPlayer().getWorld()==Main.s.getWorlds().get(0)){if(Main.allowedBlockGlobalAss.contains(event.getBlock().getType())){
                                if(Main.g.getActualDay()<Main.g.getDayAssaut())
                                {
                                    
                            event.setCancelled(true);
                            event.getPlayer().sendMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.RED+"Vous ne pouvez pas poser ça ici maintenant.");
                            }
                                else
                                    return;
                            }
                            if(!Main.allowedBlockGlobal.contains(event.getBlock().getType())){
                            event.setCancelled(true);
                            event.getPlayer().sendMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.RED+"Vous ne pouvez pas poser ça ici");
                            }
                            }
                            
                        }
                        return;
                    case "team_yellow":
                        if(regionCyan.contains(event.getBlock().getX(), event.getBlock().getY(), event.getBlock().getZ())||regionRed.contains(event.getBlock().getX(), event.getBlock().getY(), event.getBlock().getZ())){
                            if(!Main.allowedBlockAdvBase.contains(event.getBlock().getType())){
                            event.setCancelled(true);
                            event.getPlayer().sendMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.RED+"Vous ne pouvez pas poser ça ici");
                            } 
                            else{
                                if(Main.g.getActualDay()<Main.g.getDayAssaut())
                                {
                                    
                            event.setCancelled(true);
                            event.getPlayer().sendMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.RED+"Vous ne pouvez pas poser ça ici maintenant.");
                            }}
                            
                            
                                    
                        }else{
                            if(event.getPlayer().getWorld()==Main.s.getWorlds().get(0)){if(Main.allowedBlockGlobalAss.contains(event.getBlock().getType())){
                                if(Main.g.getActualDay()<Main.g.getDayAssaut())
                                {
                                    
                            event.setCancelled(true);
                            event.getPlayer().sendMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.RED+"Vous ne pouvez pas poser ça ici maintenant.");
                            }
                                else
                                    return;
                            }
                            if(!Main.allowedBlockGlobal.contains(event.getBlock().getType())){
                            event.setCancelled(true);
                            event.getPlayer().sendMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.RED+"Vous ne pouvez pas poser ça ici");
                            }}
                        }
                        return;
                    case "team_red":
                        if(regionYellow.contains(event.getBlock().getX(), event.getBlock().getY(), event.getBlock().getZ())||regionCyan.contains(event.getBlock().getX(), event.getBlock().getY(), event.getBlock().getZ())){
                            if(!Main.allowedBlockAdvBase.contains(event.getBlock().getType())){
                            event.setCancelled(true);
                            event.getPlayer().sendMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.RED+"Vous ne pouvez pas poser ça ici");
                            }
                            else{
                                if(Main.g.getActualDay()<Main.g.getDayAssaut())
                                {
                                    
                            event.setCancelled(true);
                            event.getPlayer().sendMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.RED+"Vous ne pouvez pas poser ça ici maintenant.");
                            }}
                            
                            
                                    
                        }else{
                            if(event.getPlayer().getWorld()==Main.s.getWorlds().get(0)){
                                if(Main.allowedBlockGlobalAss.contains(event.getBlock().getType())){
                                if(Main.g.getActualDay()<Main.g.getDayAssaut())
                                {
                                    
                            event.setCancelled(true);
                            event.getPlayer().sendMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.RED+"Vous ne pouvez pas poser ça ici maintenant.");
                            }
                                else
                                    return;
                            }
                            if(!Main.allowedBlockGlobal.contains(event.getBlock().getType())){
                            event.setCancelled(true);
                            event.getPlayer().sendMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.RED+"Vous ne pouvez pas poser ça ici");
                            }
                            }
                        }
                        return;
                        
                
            }
        }
    }
}
