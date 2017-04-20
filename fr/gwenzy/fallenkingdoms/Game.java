/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gwenzy.fallenkingdoms;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

/**
 *
 * @author gwend
 */
public class Game implements Serializable{
    private List<UUID> players;
    private int dayPVP;
    private int dayAssaut;
    private int actualDay;
    private int actualMin;
    private int actualSec;
    private boolean isRunning;
    
    private String state;
    
    private HashMap<String, List<UUID>> teams;
    private transient HashMap<String, ProtectedRegion> bases;
    
    public Game(){
        this.players = new ArrayList<UUID>();
        this.teams = new HashMap<>();
        this.bases = new HashMap<>();
        
        this.teams.put("yellow", new ArrayList<>());
        this.teams.put("cyan", new ArrayList<>());
        this.teams.put("red", new ArrayList<>());
        
        this.bases.put("yellow", null);
        this.bases.put("cyan", null);
        this.bases.put("red", null);
        
        this.dayPVP=2;
        this.dayAssaut=3;
        this.actualDay=1;
        this.actualMin=1;
        this.actualSec=0;
        this.state = "Setup";
        
        this.isRunning=false;
    }
    
    public List<UUID> getPlayers(){
        return this.players;
    }
    
    public int getDayPVP(){
        return this.dayPVP;
    }
    
    public int getDayAssaut(){
        return this.dayAssaut;
    }
    
    public int getActualMin(){
        return this.actualMin;
    }
    
    public int getActualDay(){
        return this.actualDay;
    }
    
    public int getActualSec(){
        return this.actualSec;
    }
    
    public String getState(){
        return this.state;
    }
    
    public ProtectedRegion getTeamRegion(String team){

        return this.bases.get(team);

    }public void setTeamRegion(String team, ProtectedRegion region){

        this.bases.put(team, region);

    }public List<UUID> getTeamPlayers(String team){

        return this.teams.get(team);

    }
    
    public void reInitBases(){
        this.bases = new HashMap<>();
    }
    public void addPlayer(UUID uuid, String team){
        this.players.add(uuid);
        this.teams.get(team).add(uuid);
        
        switch(team){
            case "yellow":
                Main.s.getPlayer(uuid).setPlayerListName(ChatColor.YELLOW+Main.s.getPlayer(uuid).getName());
                break;
            case "cyan":
                Main.s.getPlayer(uuid).setPlayerListName(ChatColor.AQUA+Main.s.getPlayer(uuid).getName());
                    break;
            case "red":
                Main.s.getPlayer(uuid).setPlayerListName(ChatColor.RED+Main.s.getPlayer(uuid).getName());
                break;
            default:
                Main.s.getPlayer(uuid).setPlayerListName(Main.s.getPlayer(uuid).getName());
                
        }
        
    }
    
    public void setDayPVP(int dayPVP){
        this.dayPVP=dayPVP;
    }
    
    public void setDayAssaut(int dayAssaut){
        this.dayAssaut=dayAssaut;
    }
    
    public void setActualDay(int day){
        this.actualDay=day;
    }
    
    public void setActualMin(int min){
        this.actualMin=min;
    }
    
    public boolean isRunning(){
        return this.isRunning;
    }
    
    public void setActualSec(int sec){
        this.actualSec=sec;
    }
    
    public void setState(String state){
        this.state=state;
    }

    public String getTimer() {
        return String.format("%02d", this.actualMin)+":"+String.format("%02d", this.actualSec);
    }
    
    public String getTeam(UUID uuid){
        if(this.teams.get("cyan").contains(uuid))
            return "cyan";
        else if(this.teams.get("yellow").contains(uuid))
            return "yellow";
        else if(this.teams.get("red").contains(uuid))
            return "red";
        else
            return null;
    }
    public boolean checkIsInitialized(){
        if(this.teams.get("yellow").size()==this.teams.get("cyan").size()&&this.teams.get("cyan").size()==this.teams.get("red").size())
            if(this.bases.get("yellow")!=null&&this.bases.get("red")!=null&&this.bases.get("cyan")!=null){
                
                for(UUID uuid : this.players)
                {
                    if(Main.s.getPlayer(uuid)==null)
                        return false;
                }
                return true;
            }
        
        return false;
    }
    public void setRunning(boolean isRunning){
        this.isRunning = isRunning;
    }
    public void start()
    {
        if(this.checkIsInitialized()){
            for(World w : Main.s.getWorlds()){
               w.setPVP(false);
            }
            this.setState("Running");
            this.setRunning(true);
            Main.s.broadcastMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.WHITE+"Le FK vient de commencer !");
        }
    }
    
    public void pause(){
                    Main.s.broadcastMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.WHITE+"Le FK a été mis en pause !");

        this.setState("Pause...");
        this.setRunning(false);
    }public void resume(){
                    Main.s.broadcastMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.WHITE+"Le FK a été repris !");
        this.setState("Running");
        this.setRunning(true);
    }
    
    public void updateScoreboard(){
        try{Main.scoreboard.getObjective("fk").unregister();}catch(Exception e){}
        Objective o = Main.scoreboard.registerNewObjective("fk", "dummy");
                                o.setDisplaySlot(DisplaySlot.SIDEBAR);

        o.setDisplayName("------FK 1------");
                        o.getScore("§6State: §b"+Main.g.getState()).setScore(0);
                        o.getScore("§6DayPVP: §b"+Main.g.getDayPVP()).setScore(-3);
                        o.getScore("§6Day: §b"+Main.g.getActualDay()).setScore(-4);
                        o.getScore("§6Players: §b"+Main.g.getPlayers().size()).setScore(-1);
                        o.getScore("").setScore(-2);
                        o.getScore("§6Timer: §b"+Main.g.getTimer()).setScore(-5);
                        
        for(UUID uuid : this.teams.get("cyan")){
            try{Main.s.getPlayer(uuid).setPlayerListName(ChatColor.AQUA+Main.s.getPlayer(uuid).getName());}catch(Exception e){}
        }
        
    }
    
    public void saveGame(){
        try {
            FileOutputStream fos;
            ObjectOutputStream oos;
            
            fos = new FileOutputStream("plugins/gameClass.data");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
