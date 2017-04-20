/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gwenzy.fallenkingdoms.events;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import fr.gwenzy.fallenkingdoms.Main;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

/**
 *
 * @author gwend
 */
public class BlockBreakListener implements Listener{
    
    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event){
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
                            
                            event.setCancelled(true);
                            event.getPlayer().sendMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.RED+"Vous ne pouvez pas casser ceci");
                        }
                        return;
                    case "team_yellow":
                        if(regionCyan.contains(event.getBlock().getX(), event.getBlock().getY(), event.getBlock().getZ())||regionRed.contains(event.getBlock().getX(), event.getBlock().getY(), event.getBlock().getZ())){
                            
                            event.setCancelled(true);
                            event.getPlayer().sendMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.RED+"Vous ne pouvez pas casser ceci");
                            
                            
                            
                                    
                        }
                        return;
                    case "team_red":
                        if(regionYellow.contains(event.getBlock().getX(), event.getBlock().getY(), event.getBlock().getZ())||regionCyan.contains(event.getBlock().getX(), event.getBlock().getY(), event.getBlock().getZ())){
                            event.setCancelled(true);
                            event.getPlayer().sendMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.RED+"Vous ne pouvez pas casser ceci");
                        
                        }
                        return;
                        
                
            }
        }
        }
    
    @EventHandler
public void onPlayerTeleport(PlayerTeleportEvent event){
    if(event.getCause()==TeleportCause.ENDER_PEARL)
    {
        
        if(Main.g.getTeamRegion(Main.g.getTeam(event.getPlayer().getUniqueId())).contains(event.getTo().getBlockX(), event.getTo().getBlockY(), event.getTo().getBlockZ())){
                return;
            }
            else{
                ProtectedRegion regionCyan = Main.g.getTeamRegion("cyan");
                ProtectedRegion regionYellow = Main.g.getTeamRegion("yellow");
                ProtectedRegion regionRed = Main.g.getTeamRegion("red");
            switch("team_"+Main.g.getTeam(event.getPlayer().getUniqueId())){
                    case "team_cyan":
                        
                        if(regionYellow.contains(event.getTo().getBlockX(), event.getTo().getBlockY(), event.getTo().getBlockZ())||regionRed.contains(event.getTo().getBlockX(), event.getTo().getBlockY(), event.getTo().getBlockZ())){
                            
                            event.setCancelled(true);
                            event.getPlayer().sendMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.RED+"Vous ne pouvez pas vous téléporter ici");
                        }
                        return;
                    case "team_yellow":
                        if(regionCyan.contains(event.getTo().getBlockX(), event.getTo().getBlockY(), event.getTo().getBlockZ())||regionRed.contains(event.getTo().getBlockX(), event.getTo().getBlockY(), event.getTo().getBlockZ())){
                            
                            event.setCancelled(true);
                            event.getPlayer().sendMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.RED+"Vous ne pouvez pas vous téléporter ici");
                            
                            
                            
                                    
                        }
                        return;
                    case "team_red":
                        if(regionYellow.contains(event.getTo().getBlockX(), event.getTo().getBlockY(), event.getTo().getBlockZ())||regionCyan.contains(event.getTo().getBlockX(), event.getTo().getBlockY(), event.getTo().getBlockZ())){
                            event.setCancelled(true);
                            event.getPlayer().sendMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.RED+"Vous ne pouvez pas vous téléporter ici");
                        
                        }
                        return;
                        
                
            }
        }
        }
    }
}
    




