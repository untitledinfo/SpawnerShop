package com.spawnershop.integration;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import java.util.*;

/** Detects optional plugins without linking to their implementation classes. */
public final class IntegrationManager {
  private static final Set<String> SUPPORTED = Set.of(
      "PlaceholderAPI", "LuckPerms", "Essentials", "CMI", "DecentHolograms",
      "SilkSpawners", "MineableSpawners", "RoseStacker", "WildStacker", "SmartSpawner",
      "ItemsAdder", "Oraxen", "MMOItems", "EconomyShopGUI", "Citizens", "ProtocolLib");
  private final Set<String> enabled;

  public IntegrationManager(Plugin plugin) {
    PluginManager manager = plugin.getServer().getPluginManager();
    Set<String> found = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
    for (String name : SUPPORTED) if (manager.isPluginEnabled(name)) found.add(name);
    enabled = Collections.unmodifiableSet(found);
    if (enabled.contains("SmartSpawner")) plugin.getLogger().info("SmartSpawner detected; enable its adapter only when its documented API is available.");
  }
  public boolean enabled(String name) { return enabled.contains(name); }
  public Set<String> enabled() { return enabled; }
}
