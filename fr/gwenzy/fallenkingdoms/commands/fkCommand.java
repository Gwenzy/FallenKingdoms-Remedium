/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gwenzy.fallenkingdoms.commands;

import fr.gwenzy.fallenkingdoms.Game;
import fr.gwenzy.fallenkingdoms.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

/**
 *
 * @author gwend
 */
public class fkCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(sender.hasPermission("fallenkingdoms.*")||sender.hasPermission("fallenkingdoms."+commandLabel)){
            if(commandLabel.equalsIgnoreCase("fk")){
                if(args.length>0){
                    if(args[0].equalsIgnoreCase("init")){
                        //Initialisation des scoreboards
                        Main.g = new Game();
                        
                        Main.scoreboard = Main.s.getScoreboardManager().getNewScoreboard();
                        Objective o = Main.scoreboard.registerNewObjective("fk", "dummy");
                        o.setDisplayName("------FK 1------");
                        o.getScore("§6State: §b"+Main.g.getState()).setScore(0);
                        o.getScore("§6DayPVP: §b"+Main.g.getDayPVP()).setScore(-3);
                        o.getScore("§6Day: §b"+Main.g.getActualDay()).setScore(-4);
                        o.getScore("§6Players: §b"+Main.g.getPlayers().size()).setScore(-1);
                        o.getScore("").setScore(-2);
                        o.getScore("§6Timer: §b"+Main.g.getTimer()).setScore(-5);
                        
                        o.setDisplaySlot(DisplaySlot.SIDEBAR);
                        for(Player p : Main.s.getOnlinePlayers()){
                            p.setScoreboard(Main.scoreboard);
                        }
                        
                        sender.sendMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.GREEN+"Scoreboard créé !");
                        
                    }
                    if(args[0].equalsIgnoreCase("setup")&&sender instanceof Player){
                        Player p = (Player) sender;
                        Inventory inv = Main.s.createInventory(null, 9, ChatColor.GOLD+Main.PREFIX+ChatColor.BLACK+"Configuration");
                        
                        
                        ItemStack addPlayer = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                        ItemMeta addPlayerMeta = addPlayer.getItemMeta();
                        addPlayerMeta.setDisplayName(ChatColor.GOLD+"Ajouter un joueur");
                        addPlayer.setItemMeta(addPlayerMeta);
                        
                        ItemStack setBase = new ItemStack(Material.BANNER, 1);
                        ItemMeta setBaseMeta = setBase.getItemMeta();
                        setBaseMeta.setDisplayName(ChatColor.GOLD+"Définir les régions des bases");
                        setBase.setItemMeta(setBaseMeta);
                        
                        ItemStack launch = new ItemStack(Material.WOOL, 1, Main.g.checkIsInitialized()?(short) 5:(short) 14);
                        ItemMeta launchMeta = launch.getItemMeta();
                        launchMeta.setDisplayName((Main.g.checkIsInitialized()?ChatColor.GREEN:ChatColor.GRAY)+"Lancer la partie");
                        launch.setItemMeta(launchMeta);
                        
                        inv.setItem(1, addPlayer);
                        inv.setItem(2, setBase);
                        inv.setItem(7, launch);
                        
                        
                        p.openInventory(inv);
                        
                    }
                    if(args[0].equalsIgnoreCase("pause")){
                        Main.g.pause();
                    }
                    if(args[0].equalsIgnoreCase("resume")){
                        Main.g.resume();
                    }
                }
            }
        }else{
            sender.sendMessage(ChatColor.RED+Main.PREFIX+"Vous n'avez pas les permissions nécessaires pour effectuer ceci.");
        }
        
        return true;
    }
    
}
