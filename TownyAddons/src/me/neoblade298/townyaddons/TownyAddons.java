package me.neoblade298.townyaddons;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import com.palmergames.bukkit.towny.event.BonusBlockPurchaseCostCalculationEvent;
import com.palmergames.bukkit.towny.event.NewTownEvent;

public class TownyAddons extends JavaPlugin {

	public void onEnable() {
		Bukkit.getServer().getLogger().info("TownyAddons Enabled");
		// Bukkit.getPluginManager().registerEvents(this, this);
	}

	public void onDisable() {
		org.bukkit.Bukkit.getServer().getLogger().info("TownyAddons Disabled");
		super.onDisable();
	}

	@EventHandler
	public void onBonusBlockCostCalculation(BonusBlockPurchaseCostCalculationEvent e) {
		int blocks = e.getAlreadyPurchasedBlocksAmount();
		int numPurchased = e.getAmountOfPurchasingBlocksRequest();
		double cost = 0.0D;
		for (int i = 0; i < numPurchased; i++)
			cost += getBonusBlockPrice(blocks + i);
		e.setPrice(cost);
	}

	private double getBonusBlockPrice(int previousBlocks) {
		return (1000 + 2 * previousBlocks) + Math.pow(previousBlocks, 2.0D) / 200.0D;
	}

	@EventHandler
	public void onTownCreate(NewTownEvent e) {
		e.getTown().getAccount().deposit(500.0D, "Starter money");
	}
}
