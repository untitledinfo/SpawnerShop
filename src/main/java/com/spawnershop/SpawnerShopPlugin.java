package com.spawnershop;

import com.spawnershop.command.ShopCommand;
import com.spawnershop.config.ShopRepository;
import com.spawnershop.config.SellPriceRepository;
import com.spawnershop.data.StatisticsRepository;
import com.spawnershop.economy.VaultEconomy;
import com.spawnershop.gui.ShopListener;
import com.spawnershop.gui.ShopView;
import com.spawnershop.integration.IntegrationManager;
import com.spawnershop.service.TransactionService;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

/** Main entry point. No NMS or implementation-version APIs are used. */
public final class SpawnerShopPlugin extends JavaPlugin {
  private ShopRepository shops; private SellPriceRepository sellPrices; private StatisticsRepository statistics; private TransactionService transactions; private ShopView view;
  @Override public void onEnable() {
    saveDefaultConfig(); for (String file : new String[]{"messages.yml", "shops.yml", "prices.yml", "categories.yml", "sounds.yml", "animations.yml", "sell-items.yml"}) saveResource(file, false);
    shops = new ShopRepository(this); shops.reload(); sellPrices=new SellPriceRepository(this); sellPrices.reload();
    VaultEconomy economy = new VaultEconomy(this); if (!economy.connect()) { getLogger().severe("Vault economy provider unavailable; disabling."); getServer().getPluginManager().disablePlugin(this); return; }
    statistics = new StatisticsRepository(this); statistics.start();
    transactions = new TransactionService(this, economy, statistics); view = new ShopView(this, shops, transactions);
    getServer().getPluginManager().registerEvents(new ShopListener(this, view, shops, transactions), this);
    ShopCommand command = new ShopCommand(this); for(String name:new String[]{"spawnershop","sell"}){PluginCommand root=getCommand(name);if(root!=null){root.setExecutor(command);root.setTabCompleter(command);}}
    IntegrationManager integrations = new IntegrationManager(this);
    getLogger().info("Enabled with " + shops.items().size() + " items. Optional integrations: " + String.join(", ", integrations.enabled()));
  }
  @Override public void onDisable() { if (statistics != null) statistics.close(); }
  public void reloadPlugin() { reloadConfig(); shops.reload(); sellPrices.reload(); }
  public ShopView view() { return view; } public ShopRepository shops() { return shops; } public TransactionService transactions(){return transactions;} public SellPriceRepository sellPrices(){return sellPrices;}
}
