package me.blocksparty.channels;

import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class MyPlayerListener implements Listener {
	public static Channels plugin;
	
	// Getting The HashMap's For The Different Channels From The Main Class, best to just reference them. This might be your problem, also why is it static :O! Static where? and no, it isnt my problame, belive me, everything that has to do whit the hashmap works! its this line that dont work:
	Map<String, String> regional = Channels.regional;
	Map<String, String> world = Channels.world;
	Map<String, String> trade = Channels.trade;
	Map<String, String> advice = Channels.advice;
	Map<String, String> ooc = Channels.ooc;
	Map<String, String> operators = Channels.operators;
	Map<String, String> gm = Channels.gm;
	Map<String, String> pm = Channels.pm;
	
	// Auto Sets The Players That Joins The Servers To The /regional Channel!
@EventHandler
public void onPlayerJoin(PlayerJoinEvent event){
	Player player = event.getPlayer();
	
	if(Channels.regional.containsKey(player.getName())){
	return;
	}else{
		Channels.regional.put(player.getName(), "regional");
	}
}

@EventHandler
public void onPlayerChat(PlayerChatEvent event){
	Player player = event.getPlayer();
		if(regional.containsKey(player.getName())){						// Checks If The Players Is Assigned To The "regional" Channel
			 List<Entity> nearbyE = player.getNearbyEntities(100, 100, 100);
			    player.sendMessage(ChatColor.AQUA + "[REGIONAL]" + " " + ChatColor.WHITE + player.getName() + ChatColor.AQUA + ": " + ChatColor.WHITE + event.getMessage());
		        for (Entity e : nearbyE) {
		          if (e instanceof Player) {
		        		  Player p = (Player) e;
		        		  p.sendMessage(ChatColor.AQUA + "[REGIONAL]" + " " + ChatColor.WHITE + player.getName() + ChatColor.AQUA + ": " + ChatColor.WHITE + event.getMessage());
		          }
		        }
		        event.setCancelled(true);
		}else if(world.containsKey(player.getName())){
			for(Player p: player.getWorld().getPlayers()){
				p.sendMessage(ChatColor.BLUE + "[WORLD]" + " " + ChatColor.WHITE + player.getName() + ChatColor.BLUE + ": " + ChatColor.WHITE + event.getMessage());
			}
			event.setCancelled(true);
		}else if(trade.containsKey(player.getName())){
			Bukkit.broadcastMessage(ChatColor.GREEN + "[TRADE]" + " " + ChatColor.WHITE + player.getName() + ChatColor.GREEN + ": " + ChatColor.WHITE + event.getMessage());
			event.setCancelled(true);
		}else if(advice.containsKey(player.getName())){
			Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "[ADVICE]" + " " + ChatColor.WHITE + player.getName() + ChatColor.LIGHT_PURPLE + ": " + ChatColor.WHITE + event.getMessage());
			event.setCancelled(true);
		}else if(ooc.containsKey(player.getName())){
			Bukkit.broadcastMessage(ChatColor.YELLOW + "[OOC]" + " " + ChatColor.WHITE + player.getName() + ChatColor.YELLOW + ": " + ChatColor.WHITE + event.getMessage());
			event.setCancelled(true);
		}else if(operators.containsKey(player.getName())){
			for(Player p: plugin.getServer().getOnlinePlayers()){
				if(p.isOp()){
					p.sendMessage(ChatColor.BLACK + "[OP]" + " " + ChatColor.WHITE + player.getName() + ChatColor.BLACK + ": " + ChatColor.WHITE + event.getMessage());
					event.setCancelled(true);
				}
				event.setCancelled(true);
			}
			event.setCancelled(true);
		}else if(gm.containsKey(player.getName())){
			Bukkit.broadcastMessage(ChatColor.DARK_RED + "[GAMEMASTER]" + " " + ChatColor.WHITE + player.getName() + ChatColor.DARK_RED + ": " + ChatColor.WHITE + event.getMessage());
			event.setCancelled(true);
		}else if(pm.containsKey(player.getName())){
			event.setCancelled(true);			
			// If The Players Is NOT Assigned To Any Channel..
		}else{			
			// Send The Message To The Players
			player.sendMessage("----" + ChatColor.LIGHT_PURPLE + "Welcome To BPChannels" + ChatColor.WHITE + "----");
			player.sendMessage(ChatColor.AQUA + "/regional" + ChatColor.WHITE + " | " + ChatColor.LIGHT_PURPLE + "The Regional Channels!");
			player.sendMessage(ChatColor.AQUA + "/world" + ChatColor.WHITE + " | " + ChatColor.LIGHT_PURPLE + "The World Channels!");
			player.sendMessage(ChatColor.AQUA + "/trade" + ChatColor.WHITE + " | " + ChatColor.LIGHT_PURPLE + "The Trade Channels!");
			player.sendMessage(ChatColor.AQUA + "/advice" + ChatColor.WHITE + " | " + ChatColor.LIGHT_PURPLE + "The Advice Channels!");
			player.sendMessage(ChatColor.AQUA + "/ooc" + ChatColor.WHITE + " | " + ChatColor.LIGHT_PURPLE + "The OOC Channels!");
			player.sendMessage(ChatColor.AQUA + "/gm" + ChatColor.WHITE + " | " + ChatColor.LIGHT_PURPLE + "The GM Channels!");
			player.sendMessage(ChatColor.AQUA + "/rich" + ChatColor.WHITE + " | " + ChatColor.LIGHT_PURPLE + "The Rich Channels! Only The Rich Can Use This!");
			player.sendMessage("----" + ChatColor.LIGHT_PURPLE + "Welcome To BPChannels" + ChatColor.WHITE + "----");
			event.setCancelled(true);													// Cancel The Messages, And Make Sure It Don't Show Up In The Chat
		}
}
}