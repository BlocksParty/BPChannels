package me.blocksparty.channels;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;


public class Channels extends JavaPlugin{
	public final Logger logger = Logger.getLogger("Minecraft");
	public final MyPlayerListener pl = new MyPlayerListener();
	public static Channels plugin;

	// Creating The HashMap's For The Different Channels
	public static Map<String, String> regional = new HashMap<String, String>();
	public static Map<String, String> world = new HashMap<String, String>();
	public static Map<String, String> trade = new HashMap<String, String>();
	public static Map<String, String> advice = new HashMap<String, String>();
	public static Map<String, String> ooc = new HashMap<String, String>();
	public static Map<String, String> operators = new HashMap<String, String>();
	public static Map<String, String> gm = new HashMap<String, String>();

	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Has Been Disabled!");

	}

	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + ", Version " + pdfFile.getVersion() + ", Has Been Enabled!");

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.pl, this);
	}


	// Creating The Different Commands To Assign The Players To The Different Channels
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if (sender instanceof Player) { // we only want instance of player have access to the following code!
			Player player = (Player) sender;

			// The Regional Channel
			if(commandLabel.equalsIgnoreCase("regional")){ 									// If The Players Types /regional..

				if(args.length == 0){ 														// If The Length Of The ARGS Is 0 (Which = They Only Typed /regional, Without The Messages They Want To Send After The Command!
					player.sendMessage("You Have To Type In Your Message!");				// If This Is The Case, Send The Players This Message!
				}else{																		// ELSE, IF The Players DID type /regional, And DID Type The Messages They Wanted To Send After The Command..
					regional.put(player.getName(), "regional");								// Put The PlayerName To The HashMap "regional", So That The Next Message The Players Send, Will Be Assigned To Regional!
					// Remove The PlayerName From The Other HashMaps, In Case They Decided To Change Channel (Which = Make Sure Players Doesn't Talk In Multiple Channels!
					world.remove(player.getName());											
					trade.remove(player.getName());
					advice.remove(player.getName());
					ooc.remove(player.getName());
					gm.remove(player.getName());
					String fullArgs = "";													// Gets The Full ARGS The Players Typed After The Command...
					for (String arg : args) {
						fullArgs += arg + " ";
					}

					List<Entity> nearbyE = player.getNearbyEntities(100, 100, 100);		// Define The Radius The Channel Will Send The Players Message
					for (Entity e : nearbyE) {
						if (e instanceof Player) {
							Player p = (Player) e;
							player.sendMessage(ChatColor.AQUA + "[REGIONAL]" + " " + ChatColor.WHITE + player.getName() + ChatColor.AQUA + ": " + ChatColor.WHITE + fullArgs);  // Sends The Message To The Players That Typed It!
							p.sendMessage (ChatColor.AQUA + "[REGIONAL]" + " " + ChatColor.WHITE + player.getName() + ChatColor.AQUA + ": " + ChatColor.WHITE + fullArgs);		// Broadcast The Messages Through The Regional Channel!
						}
					}

				}
			}


			if(commandLabel.equalsIgnoreCase("world")){
				if(args.length == 0){
					player.sendMessage("You Have To Type In Your Message!");
				}else{
					world.put(player.getName(), "world");
					regional.remove(player.getName());
					trade.remove(player.getName());
					advice.remove(player.getName());
					ooc.remove(player.getName());
					gm.remove(player.getName());
					String fullArgs = "";
					for (String arg : args) {
						fullArgs += arg + " ";
					}
					Bukkit.broadcastMessage(ChatColor.BLUE + "[WORLD]" + " " + ChatColor.WHITE + player.getName() + ChatColor.BLUE + ": " + ChatColor.WHITE + fullArgs);
				}
			}

			if(commandLabel.equalsIgnoreCase("trade")){
				if(args.length == 0){
					player.sendMessage("You Have To Type In Your Message!");
				}else{
					trade.put(player.getName(), "trade");
					world.remove(player.getName());
					regional.remove(player.getName());
					advice.remove(player.getName());
					ooc.remove(player.getName());
					gm.remove(player.getName());
					String fullArgs = "";
					for (String arg : args) {
						fullArgs += arg + " ";
					}
					Bukkit.broadcastMessage(ChatColor.GREEN + "[TRADE]" + " " + ChatColor.WHITE + player.getName() + ChatColor.GREEN + ": " + ChatColor.WHITE + fullArgs);
				}
			}

			if(commandLabel.equalsIgnoreCase("advice")){
				if(args.length == 0){
					player.sendMessage("You Have To Type In Your Message!");
				}else{
					advice.put(player.getName(), "advice");
					world.remove(player.getName());
					trade.remove(player.getName());
					regional.remove(player.getName());
					ooc.remove(player.getName());
					gm.remove(player.getName());
					String fullArgs = "";
					for (String arg : args) {
						fullArgs += arg + " ";
					}
					Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "[ADVICE]" + " " + ChatColor.WHITE + player.getName() + ChatColor.LIGHT_PURPLE + ": " + ChatColor.WHITE + fullArgs);
				}
			}

			if(commandLabel.equalsIgnoreCase("ooc")){
				if(args.length == 0){
					player.sendMessage("You Have To Type In Your Message!");
				}else{
					ooc.put(player.getName(), "ooc");
					world.remove(player.getName());
					trade.remove(player.getName());
					advice.remove(player.getName());
					regional.remove(player.getName());
					gm.remove(player.getName());
					String fullArgs = "";
					for (String arg : args) {
						fullArgs += arg + " ";
					}
					Bukkit.broadcastMessage(ChatColor.YELLOW + "[OOC]" + " " + ChatColor.WHITE + player.getName() + ChatColor.YELLOW + ": " + ChatColor.WHITE + fullArgs);
				}
			}

			if(commandLabel.equalsIgnoreCase("operators")){
				if(player.isOp()){
					if(args.length == 0){
						player.sendMessage("You Have To Type In Your Message!");
					}else{
						operators.put(player.getName(), "operators");
						world.remove(player.getName());
						trade.remove(player.getName());
						advice.remove(player.getName());
						regional.remove(player.getName());
						ooc.remove(player.getName());
						gm.remove(player.getName());
						String fullArgs = "";
						for (String arg : args) {
							fullArgs += arg + " ";
						}
						Bukkit.broadcastMessage(ChatColor.BLACK + "[OP]" + " " + ChatColor.WHITE + player.getName() + ChatColor.BLACK + ": " + ChatColor.WHITE + fullArgs);
					}
				}else{ 
					player.sendMessage("You Do Not Have Permission To Use This Channel!");
				}
			}


			if(commandLabel.equalsIgnoreCase("gm")){
				if(player.isOp()){
					if(args.length == 0){
						player.sendMessage("You Have To Type In Your Message!");
					}else{
						gm.put(player.getName(), "ooc");
						world.remove(player.getName());
						trade.remove(player.getName());
						advice.remove(player.getName());
						regional.remove(player.getName());
						ooc.remove(player.getName());
						String fullArgs = "";
						for (String arg : args) {
							fullArgs += arg + " ";
						}
						Bukkit.broadcastMessage(ChatColor.DARK_RED + "[GAMEMASTER]" + " " + ChatColor.WHITE + player.getName() + ChatColor.DARK_RED + ": " + ChatColor.WHITE + fullArgs);
					}
				}else{ 
					player.sendMessage("You Do Not Have Permission To Use This Channel!");
				}
			}
		}
		return false;
	}
}