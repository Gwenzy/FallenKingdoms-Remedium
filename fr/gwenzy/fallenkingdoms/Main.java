/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gwenzy.fallenkingdoms;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import fr.gwenzy.fallenkingdoms.commands.fkCommand;
import fr.gwenzy.fallenkingdoms.events.BlockBreakListener;
import fr.gwenzy.fallenkingdoms.events.InventoryClickListener;
import fr.gwenzy.fallenkingdoms.events.PlayerJoinListener;
import fr.gwenzy.fallenkingdoms.events.BlockPlaceListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.Scoreboard;

/**
 *
 * @author gwend
 */
public class Main extends JavaPlugin
{
    public static final String PREFIX = "[Fallen Kingdoms] ";
    public static Scoreboard scoreboard = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
    public static Game g;
    public static WorldEditPlugin we = null;
    public static WorldGuardPlugin wg = null;
    public static List<Material> allowedBlockGlobal = new ArrayList<>();
    public static List<Material> allowedBlockAdvBase = new ArrayList<>();
    public static List<Material> allowedBlockGlobalAss = new ArrayList<>();
    
    public static int timer;
    /*
        0 = Setuping phase
        1 = Playing
        2 = Pause
    
    */
    public static int setupPhase = 0;
    
    
    public static Server s; 
    
    
    
    
    @Override
    public void onEnable(){
        boolean isOk = true;
        s = Bukkit.getServer();
        
        log_info("*******Fallen Kingdoms by Tháksín*******");
        log_info("Initializing plugin...");
        
        we = (WorldEditPlugin) s.getPluginManager().getPlugin("WorldEdit");
        wg = (WorldGuardPlugin) s.getPluginManager().getPlugin("WorldGuard");
        
        if(we==null||wg==null)
            isOk = false;
        if(isOk){
            getCommand("fk").setExecutor(new fkCommand());
            s.getPluginManager().registerEvents(new PlayerJoinListener(), this);
            s.getPluginManager().registerEvents(new InventoryClickListener(), this);

            s.getPluginManager().registerEvents(new BlockPlaceListener(), this);
            s.getPluginManager().registerEvents(new BlockBreakListener(), this);
            allowedBlockGlobal.add(Material.SIGN);
            allowedBlockGlobal.add(Material.SIGN_POST);
            allowedBlockGlobal.add(Material.TORCH);
            allowedBlockAdvBase.add(Material.TNT);
            allowedBlockAdvBase.add(Material.REDSTONE_TORCH_ON);
            allowedBlockAdvBase.add(Material.REDSTONE_TORCH_OFF);
            allowedBlockGlobalAss.add(Material.TNT);
            allowedBlockGlobalAss.add(Material.REDSTONE_TORCH_ON);
            allowedBlockGlobalAss.add(Material.REDSTONE_TORCH_OFF);
            File f = new File("plugins/gameClass.data");
            
            if(f.exists()){try{
                Main.scoreboard = Main.s.getScoreboardManager().getNewScoreboard();
                for(Player p : Main.s.getOnlinePlayers()){
                            p.setScoreboard(Main.scoreboard);
                        }
                FileInputStream fis = new FileInputStream("plugins/gameClass.data");
   ObjectInputStream ois = new ObjectInputStream(fis);
   Main.g = (Game)ois.readObject();
   World world = Main.s.getWorlds().get(0);
   Main.g.reInitBases();
       Main.g.setTeamRegion("yellow", Main.wg.getRegionManager(world).getRegion("area_yellow"));
       Main.g.setTeamRegion("cyan", Main.wg.getRegionManager(world).getRegion("area_cyan"));
       Main.g.setTeamRegion("red", Main.wg.getRegionManager(world).getRegion("area_red"));
   
   
   log_info("A data file has been read");
            }catch(Exception e){e.printStackTrace();}
            }
            
            timer = s.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
                public void run() {
                    if(g!=null){
                        if(g.isRunning()){
                        if(g.getActualMin()==0&&g.getActualSec()==0){
                            g.setActualDay(g.getActualDay()+1);
                            
                            if(g.getActualDay()>=g.getDayPVP()){
                                
                                for(World w : Main.s.getWorlds()){
                                    if(w.getPVP()==false){
                                        w.setPVP(true);
                                        Main.s.broadcastMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.WHITE+"Le PVP est désormais activé dans "+w.getName()+"!");
                                    }
            }
                            }
                            
                            if(g.getActualDay()==g.getDayAssaut()){
                              
                                        Main.s.broadcastMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.WHITE+"Les assauts sont autorisés, à l'attaque !");
                                   
            }
                            
                            g.setActualMin(1);
                            Main.s.broadcastMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.RED+"Attention ! Le jour "+(g.getActualDay()-1)+" vient de se terminer ! Début du jour "+g.getActualDay()+".");
                        }
                        else if(g.getActualSec()==0){
                            g.setActualMin(g.getActualMin()-1);
                            g.setActualSec(59);
                        }
                        else
                        {
                            g.setActualSec(g.getActualSec()-1);
                        }
                        }
                       g.updateScoreboard();
                    g.saveGame();
                    }
                    
                    
                    
                }
                
            }, 20, 20);
            log_info("Plugin started correctly ! Welcome !");
            
        }
        else
            log_info("Error while trying to start plugin, please check if WorldEdit / WorldGuard are present");
        

    }
    
    
    @Override
    public void onDisable(){
        
    }
    
    public void log_info(String message){
        s.getLogger().info(Main.PREFIX+message);
        
    }
}
