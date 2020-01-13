package com.danielgulic.autobroadcast;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public final class AutoBroadcast extends JavaPlugin {

	public static AutoBroadcast instance;
	public static AutoBroadcast get() { return instance; }

	@Override
	public void onEnable() {
		// Plugin startup logic
		instance = this;
		saveDefaultConfig(); // save default config to server
		startBroadcastTimer(getServer().getScheduler());
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
		instance = null;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase("reloadbroadcasts")) {
			reloadConfig();
			sender.sendMessage(ChatColor.GREEN + "Reloaded AutoBroadcast config and broadcasts");
			return true;
		}

		return false;
	}

	private void startBroadcastTimer(BukkitScheduler scheduler) {
		int scheduleId = scheduler.scheduleSyncDelayedTask(instance, () -> {
			if (getConfig().getBoolean("broadcasts-enabled")) {
				Set<String> broadcastsList = getConfig().getConfigurationSection("broadcasts").getKeys(false);
				String broadcastId = getRandomElement(broadcastsList);
				ConfigurationSection broadcast = getConfig().getConfigurationSection("broadcasts." + broadcastId);
				for (String message : broadcast.getStringList("messages")) {
					getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
				}
				if (broadcast.getString("sound") != null) {
					for (Player p : getServer().getOnlinePlayers()) {
						try {
							p.playSound(p.getLocation(), Sound.valueOf(broadcast.getString("sound")), 5, 5);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			startBroadcastTimer(scheduler);
		}, getConfig().getLong("broadcast-interval"));
	}

	private String getRandomElement(Set<String> set) {
		int index = new Random().nextInt(set.size());
		Iterator<String> iter = set.iterator();
		for (int i = 0; i < index; i++) {
			iter.next();
		}
		return iter.next();
	}
}
