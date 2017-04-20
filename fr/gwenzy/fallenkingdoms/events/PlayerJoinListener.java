/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gwenzy.fallenkingdoms.events;

import fr.gwenzy.fallenkingdoms.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 *
 * @author gwend
 */
public class PlayerJoinListener implements Listener{
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        event.getPlayer().setScoreboard(Main.scoreboard);
    }
}
