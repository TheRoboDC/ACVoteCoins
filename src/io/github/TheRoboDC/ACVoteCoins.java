package io.github.TheRoboDC;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ACVoteCoins extends JavaPlugin {
	
	HashMap<String, Integer> CoinsDatabase = new HashMap<String, Integer>();
	
	@Override
	public void onEnable() {
		getLogger().info("ACVoteCoins has been enabled and will now listen for votes.");
		File configFile = new File(this.getDataFolder() + "/config.yml");
		if(!configFile.exists()) {
		  this.saveDefaultConfig();
		}
		List <String> s = getConfig().getStringList("coinsdatabase");
		for (String str : s) {
			String[] words = str.split(":");
			Integer valueConverted = Integer.parseInt(words[1]);
			CoinsDatabase.put(words[0], valueConverted);
		}
	}
	
	@Override
	public void onDisable() {
		getLogger().info("ACVoteCoins has been disabled and has saved all player information.");
		List <String> s = getConfig().getStringList("coinsdatabase");
		for(Entry <String, Integer> entry : CoinsDatabase.entrySet()) {
			entry.getKey();
			entry.getValue();
			s.add(entry.getKey() + ":" + entry.getValue());
		}
		getConfig().set("coinsdatabase", s);
		saveConfig();
	}
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String args[]) {
		if (commandLabel.equalsIgnoreCase("addvotecoin") && args.length == 1) {
			ConsoleCommandSender console = (ConsoleCommandSender) sender;
			Player playerArgument1 = console.getServer().getPlayer(args [0]);
			String playerArgument1Name = playerArgument1.getName();
			if (CoinsDatabase.containsKey(playerArgument1)) {
			String VoteCoinsPerVoteRaw = this.getConfig().getString("NumOfCoins");
			Integer VoteCoinsPerVoteConverted = Integer.parseInt(VoteCoinsPerVoteRaw);
			int CurrentVoteCoins = CoinsDatabase.get(playerArgument1);
			int NewBalance = CurrentVoteCoins + VoteCoinsPerVoteConverted;
			CoinsDatabase.put(playerArgument1Name, NewBalance);
			} else {
				int oneCoin = 1;
				CoinsDatabase.put(playerArgument1Name, oneCoin);
			}
		}
		
		if (commandLabel.equalsIgnoreCase("vote")) {
			Player player = (Player) sender;
			String playerName = player.getName();
			if (args.length == 0) {
			String voteLink1 = this.getConfig().getString("VotingLinks" + "." + "one");
			String voteLink2 = this.getConfig().getString("VotingLinks" + "." + "two");
			String voteLink3 = this.getConfig().getString("VotingLinks" + "." + "three");
			String voteLink4 = this.getConfig().getString("VotingLinks" + "." + "four");
			String voteLink5= this.getConfig().getString("VotingLinks" + "." + "five");
			player.sendMessage(ChatColor.YELLOW + "-------------=[ " + ChatColor.DARK_AQUA + "Vote Links" + ChatColor.YELLOW + " ]=-------------");
			player.sendMessage("");
			player.sendMessage(ChatColor.DARK_PURPLE + "Vote on the below links for Vote Coins!");
			player.sendMessage("");
			player.sendMessage(ChatColor.YELLOW + voteLink1);
			player.sendMessage(ChatColor.YELLOW + voteLink2);
			player.sendMessage(ChatColor.YELLOW + voteLink3);
			player.sendMessage(ChatColor.YELLOW + voteLink4);
			player.sendMessage(ChatColor.YELLOW + voteLink5);
			player.sendMessage("");
			player.sendMessage(ChatColor.DARK_AQUA + "Type /vote help for help and commands.");
			} else if (args.length == 1) {
				if (args [0].equalsIgnoreCase("help")) {
					player.sendMessage(ChatColor.YELLOW + "-------------=[ " + ChatColor.DARK_AQUA + "Vote Commands" + ChatColor.YELLOW + " ]=-------------");
					player.sendMessage("");
					player.sendMessage(ChatColor.DARK_AQUA + "/vote - Tells you the voting links.");
					player.sendMessage(ChatColor.DARK_AQUA + "/votecoins - Tells you how many Vote Coins you have.");
					player.sendMessage(ChatColor.DARK_AQUA + "/vote rewards - Tells you the list of rewards.");
					player.sendMessage(ChatColor.DARK_AQUA + "/vote claim [id] - Claims the specific reward if you have enough coins.");
				}
					if (args [0].equalsIgnoreCase("rewards")) {
						String reward1 = this.getConfig().getString("OtherRewardsNames" + "." + "one");
						String reward2 = this.getConfig().getString("OtherRewardsNames" + "." + "two");
						String reward3 = this.getConfig().getString("OtherRewardsNames" + "." + "three");
						String reward4 = this.getConfig().getString("OtherRewardsNames" + "." + "four");
						String moneyToReceive = this.getConfig().getString("Money" + "." + "AmountOfMoney");
						String costReward1 = this.getConfig().getString("OtherRewardsNames" + "." + "costOfOne");
						String costReward2 = this.getConfig().getString("OtherRewardsNames" + "." + "costOfTwo");
						String costReward3 = this.getConfig().getString("OtherRewardsNames" + "." + "costOfThree");
						String costReward4 = this.getConfig().getString("OtherRewardsNames" + "." + "costOfFour");
						String costRewardMoney = this.getConfig().getString("Money" + "." + "VoteCoinsCost");
						player.sendMessage(ChatColor.YELLOW + "-------------=[ " + ChatColor.DARK_AQUA + "Vote Rewards" + ChatColor.YELLOW + " ]=-------------");
						player.sendMessage("");
						player.sendMessage(ChatColor.DARK_AQUA + "ID 1. $" + moneyToReceive + ChatColor.DARK_PURPLE + " - " + costRewardMoney + " Coins!");
						player.sendMessage(ChatColor.DARK_AQUA + "ID 2. 1 " + reward1 + ChatColor.DARK_PURPLE + " - " + costReward1 + " Coins!");
						player.sendMessage(ChatColor.DARK_AQUA + "ID 3. 1 " + reward2 + ChatColor.DARK_PURPLE + " - " + costReward2 + " Coins!");
						player.sendMessage(ChatColor.DARK_AQUA + "ID 4. 1 " + reward3 + ChatColor.DARK_PURPLE + " - " + costReward3 + " Coins!");
						player.sendMessage(ChatColor.DARK_AQUA + "ID 5. 1 " + reward4 + ChatColor.DARK_PURPLE + " - " + costReward4 + " Coins!");
						player.sendMessage("");
						player.sendMessage(ChatColor.DARK_AQUA + "Type " + ChatColor.DARK_PURPLE + "/vote claim [id] to redeem a reward!");
						
					}
			} 
			if (args.length == 2) {
				if (args [0].equalsIgnoreCase("claim")) {
					if (CoinsDatabase.containsKey(player)) {
					if (args [1].equalsIgnoreCase("2")) {
						int VoteCoinBalance = CoinsDatabase.get(player);
						String costReward1 = this.getConfig().getString("OtherRewardsNames" + "." + "costOfOne");
						Integer costReward1Converted = Integer.parseInt(costReward1);
						if (VoteCoinBalance >= costReward1Converted) {
							VoteCoinBalance -= costReward1Converted;
							CoinsDatabase.put(playerName, VoteCoinBalance);
							String itemReward1 = this.getConfig().getString("OtherRewards" + "." + "one");
							Material material = Material.getMaterial(itemReward1);
							getServer().dispatchCommand(getServer().getConsoleSender(), "give " + player.getName() + " 1 " + material);
							player.sendMessage(ChatColor.YELLOW + "You have sucessfully purchased 1 " + material + ". You now have " + ChatColor.RED + CoinsDatabase.get(player) + " coins.");
						}
					} else if (args [1].equalsIgnoreCase("3")) {
						int VoteCoinBalance = CoinsDatabase.get(player);
						String costReward2 = this.getConfig().getString("OtherRewardsNames" + "." + "costOfTwo");
						Integer costReward2Converted = Integer.parseInt(costReward2);
						if (VoteCoinBalance >= costReward2Converted) {
							VoteCoinBalance -= costReward2Converted;
							CoinsDatabase.put(playerName, VoteCoinBalance);
							String itemReward2 = this.getConfig().getString("OtherRewards" + "." + "two");
							Material material = Material.getMaterial(itemReward2);
							getServer().dispatchCommand(getServer().getConsoleSender(), "give " + player.getName() + " 1 " + material);
							player.sendMessage(ChatColor.YELLOW + "You have sucessfully purchased 1 " + material + ". You now have " + ChatColor.RED + CoinsDatabase.get(player) + " coins.");
					    }
				     } else if (args [1].equalsIgnoreCase("4")) {
				    	 int VoteCoinBalance = CoinsDatabase.get(player);
				    	 String costReward3 = this.getConfig().getString("OtherRewardsNames" + "." + "costOfThree");
					     Integer costReward3Converted = Integer.parseInt(costReward3);
				    	 if (VoteCoinBalance >= costReward3Converted) {
				    		 VoteCoinBalance -= costReward3Converted;
				    		 CoinsDatabase.put(playerName, VoteCoinBalance);
				    		 String itemReward3 = this.getConfig().getString("OtherRewards" + "." + "three");
				    		 Material material = Material.getMaterial(itemReward3);
				    		 getServer().dispatchCommand(getServer().getConsoleSender(), "give " + player.getName() + " 1 " + material);
				    		 player.sendMessage(ChatColor.YELLOW + "You have sucessfully purchased 1 " + material + ". You now have " + ChatColor.RED + CoinsDatabase.get(player) + " coins."); 
				    	 }
				     } else if (args [1].equalsIgnoreCase("5")) {
				    	 int VoteCoinBalance = CoinsDatabase.get(player);
				    	 String costReward4 = this.getConfig().getString("OtherRewardsNames" + "." + "costOfFour");
				    	 Integer costReward4Converted = Integer.parseInt(costReward4);
				    	 if (VoteCoinBalance >= costReward4Converted) {
				    		 VoteCoinBalance -= costReward4Converted;
				    		 CoinsDatabase.put(playerName, VoteCoinBalance);
				    		 String itemReward4 = this.getConfig().getString("OtherRewards" + "." + "four");
				    		 Material material = Material.getMaterial(itemReward4);
				    		 getServer().dispatchCommand(getServer().getConsoleSender(), "give " + player.getName() + " 1 " + material);
				    		 player.sendMessage(ChatColor.YELLOW + "You have sucessfully purchased 1 " + material + ". You now have " + ChatColor.RED + CoinsDatabase.get(player) + " coins."); 
				    	 }
				     } else if (args [1].equalsIgnoreCase("1")) {
				    	 int VoteCoinBalance = CoinsDatabase.get(player);
				    	 String costOfMoney = this.getConfig().getString("Money" + "." + "VoteCoinsCost");
				    	 Integer costOfMoneyConverted = Integer.parseInt(costOfMoney);
				    	 if (VoteCoinBalance >= costOfMoneyConverted) {
				    		 VoteCoinBalance -= costOfMoneyConverted;
				    		 CoinsDatabase.put(playerName, VoteCoinBalance);
				    		 String moneyToGive = this.getConfig().getString("Money" + "." + "AmountOfMoney");
				    		 getServer().dispatchCommand(getServer().getConsoleSender(), "eco give " + player.getName() + " " + moneyToGive);
				    		 player.sendMessage(ChatColor.YELLOW + "You have sucessfully purchased $" + moneyToGive + ". You now have " + ChatColor.RED + CoinsDatabase.get(player) + " coins.");
				    	 }
				     } else {
				    	 player.sendMessage(ChatColor.RED + "There was no such ID found!");
				     }
			    } else {
			    	player.sendMessage(ChatColor.DARK_AQUA + "You have never voted for this server.");
			    }
				}
		} 
		
	} if (commandLabel.equals("votecoins")) {
		Player player = (Player) sender;
		if (CoinsDatabase.containsKey(player)) {
		int VoteCoinBalance = CoinsDatabase.get(player);
		player.sendMessage(ChatColor.YELLOW + "You currently have " + VoteCoinBalance + ChatColor.RED + " coins.");
		} else {
			player.sendMessage(ChatColor.YELLOW + "You currently have 0" + ChatColor.RED + " coins.");
		}
	}
		return false;

    }
	

}
