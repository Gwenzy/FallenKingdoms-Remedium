/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gwenzy.fallenkingdoms.events;

import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Selection;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sun.prism.paint.Color;
import fr.gwenzy.fallenkingdoms.Main;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

/**
 *
 * @author gwend
 */
public class InventoryClickListener implements Listener{
    
    @EventHandler
    public void onPlayerClick(InventoryClickEvent event){
        if(event.getInventory().getName().equals(ChatColor.GOLD+Main.PREFIX+ChatColor.BLACK+"Configuration")){
            if(event.getClick()==ClickType.LEFT){
                if(event.getCurrentItem()!=null){
                    if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD+"Ajouter un joueur")){
                        event.setCancelled(true);
                        event.getWhoClicked().closeInventory();
                        
                        Inventory inv = Main.s.createInventory(null, 9, ChatColor.GOLD+Main.PREFIX+ChatColor.BLACK+"Ajouter joueur");
                        
                        for(Player p : Main.s.getOnlinePlayers()){
                            if(!Main.g.getPlayers().contains(p.getUniqueId())){
                            ItemStack addPlayer = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                            SkullMeta addPlayerMeta = (SkullMeta) addPlayer.getItemMeta();
                            addPlayerMeta.setDisplayName(ChatColor.GOLD+p.getName());
                            addPlayerMeta.setOwner(p.getName());
                            addPlayer.setItemMeta(addPlayerMeta);

                            inv.addItem(addPlayer);
                            }
                        }
                        
                        event.getWhoClicked().openInventory(inv);
                    }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN+"Lancer la partie")){
                        event.setCancelled(true);
                        event.getWhoClicked().closeInventory();
                        
                        Main.g.start();
                        
                    }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD+"Définir les régions des bases")){
                        event.setCancelled(true);
                        event.getWhoClicked().closeInventory();
                        
                        Inventory inv = Main.s.createInventory(null, 9, ChatColor.GOLD+Main.PREFIX+ChatColor.BLACK+"Définition de base");
                        ItemStack addPlayer = new ItemStack(Material.WOOL, 1, (short) 3);
                            ItemMeta addPlayerMeta = addPlayer.getItemMeta();
                            addPlayerMeta.setDisplayName(ChatColor.AQUA+"Cyan");
                            List<String> lore = new ArrayList<>();
                            lore.add(Main.g.getTeamRegion("cyan")==null?ChatColor.RED+"Zone non définie":ChatColor.GREEN+"Zone OK");
                            addPlayerMeta.setLore(lore);
                            addPlayer.setItemMeta(addPlayerMeta);

                            inv.setItem(3, addPlayer);
                            
                            addPlayer = new ItemStack(Material.WOOL, 1, (short) 4);
                            addPlayerMeta = addPlayer.getItemMeta();
                            addPlayerMeta.setDisplayName(ChatColor.AQUA+"Yellow");
                            lore = new ArrayList<>();
                            lore.add(Main.g.getTeamRegion("yellow")==null?ChatColor.RED+"Zone non définie":ChatColor.GREEN+"Zone OK");
                            addPlayerMeta.setLore(lore);
                            addPlayer.setItemMeta(addPlayerMeta);
                            inv.setItem(4, addPlayer);
                            
                            addPlayer = new ItemStack(Material.WOOL, 1, (short) 14);
                            addPlayerMeta = addPlayer.getItemMeta();
                            addPlayerMeta.setDisplayName(ChatColor.AQUA+"Red");
                            lore = new ArrayList<>();
                            lore.add(Main.g.getTeamRegion("red")==null?ChatColor.RED+"Zone non définie":ChatColor.GREEN+"Zone OK");
                            addPlayerMeta.setLore(lore);
                            addPlayer.setItemMeta(addPlayerMeta);
                            inv.setItem(5, addPlayer);
                        
                        event.getWhoClicked().openInventory(inv);
                        
                                            }
                }
            }
            
            
            
        }
        else if(event.getInventory().getName().equals(ChatColor.GOLD+Main.PREFIX+ChatColor.BLACK+"Ajouter joueur")){
            if(event.getClick()==ClickType.LEFT){
                if(event.getCurrentItem()!=null){
                    
                        event.setCancelled(true);
                        Inventory inv = Main.s.createInventory(null, 9, ChatColor.BLACK+"Équipe de "+ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
                        
                        
                            ItemStack addPlayer = new ItemStack(Material.WOOL, 1, (short) 3);
                            ItemMeta addPlayerMeta = addPlayer.getItemMeta();
                            addPlayerMeta.setDisplayName(ChatColor.AQUA+"Cyan");
                            List<String> lore = new ArrayList<>();
                            lore.add("Joueurs dans cette équipe :");
                            for(UUID uuid : Main.g.getTeamPlayers("cyan")){
                                lore.add("- "+Main.s.getPlayer(uuid).getName());
                            }
                            addPlayerMeta.setLore(lore);
                            addPlayer.setItemMeta(addPlayerMeta);

                            inv.setItem(3, addPlayer);
                            
                            addPlayer = new ItemStack(Material.WOOL, 1, (short) 4);
                            addPlayerMeta = addPlayer.getItemMeta();
                            addPlayerMeta.setDisplayName(ChatColor.AQUA+"Yellow");
                            lore = new ArrayList<>();
                            lore.add("Joueurs dans cette équipe :");
                            for(UUID uuid : Main.g.getTeamPlayers("yellow")){
                                lore.add("- "+Main.s.getPlayer(uuid).getName());
                            }
                            addPlayerMeta.setLore(lore);
                            addPlayer.setItemMeta(addPlayerMeta);
                            inv.setItem(4, addPlayer);
                            
                            addPlayer = new ItemStack(Material.WOOL, 1, (short) 14);
                            addPlayerMeta = addPlayer.getItemMeta();
                            addPlayerMeta.setDisplayName(ChatColor.AQUA+"Red");
                            lore = new ArrayList<>();
                            lore.add("Joueurs dans cette équipe :");
                            for(UUID uuid : Main.g.getTeamPlayers("red")){
                                lore.add("- "+Main.s.getPlayer(uuid).getName());
                            }
                            addPlayerMeta.setLore(lore);
                            addPlayer.setItemMeta(addPlayerMeta);
                            inv.setItem(5, addPlayer);
                        
                        event.getWhoClicked().openInventory(inv);
                    }
                }
            }
        else if(event.getInventory().getName().startsWith(ChatColor.BLACK+"Équipe de ")){
            if(event.getClick()==ClickType.LEFT){
                if(event.getCurrentItem()!=null){
                    String teamName = event.getCurrentItem().getItemMeta().getDisplayName();
                    String team = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName().toLowerCase());
                    event.setCancelled(true);
                    
                    Main.g.addPlayer(Main.s.getPlayer(event.getInventory().getName().split(" ")[2]).getUniqueId(), team);
                       
                    event.getWhoClicked().sendMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.GREEN+ChatColor.stripColor(event.getInventory().getName().split(" ")[2])+" a rejoint l'équipe "+teamName+ChatColor.GREEN+".");
                        event.getWhoClicked().closeInventory();
                    }
                }
            }
        else if(event.getInventory().getName().equals(ChatColor.GOLD+Main.PREFIX+ChatColor.BLACK+"Définition de base")){
            if(event.getClick()==ClickType.LEFT){
                if(event.getCurrentItem()!=null){
                    String team = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName().toLowerCase());
                        event.setCancelled(true);
                        
                        Selection sel = Main.we.getSelection((Player) event.getWhoClicked());
                        if(sel.getLength()==31&&sel.getWidth()==31){
                            Location pt1 = sel.getMinimumPoint();
                            pt1.setY(0);
                            Location pt2 = sel.getMaximumPoint();
                            pt2.setY(255);
                            CuboidSelection newSelection = new CuboidSelection(event.getWhoClicked().getWorld(), pt1, pt2);
                            ProtectedCuboidRegion regionTeam = new ProtectedCuboidRegion("area_"+team, newSelection.getNativeMinimumPoint().toBlockVector(), newSelection.getNativeMaximumPoint().toBlockVector());
                            Main.wg.getRegionManager(event.getWhoClicked().getWorld()).addRegion(regionTeam);
                            Main.g.setTeamRegion(team, regionTeam);
                            event.getWhoClicked().sendMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.GREEN+"Zone créée pour cette équipe.");
                            
                            
                        }
                        else{
                            event.getWhoClicked().sendMessage(ChatColor.GOLD+Main.PREFIX+ChatColor.RED+"Assurez-vous que la zone sélectionnée fait bien 31x31.");

                        }
                        
                        event.getWhoClicked().closeInventory();
                    }
                }
            }
            
            
            
        }
    }
