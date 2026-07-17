package com.spawnershop.config;

import org.bukkit.Material; import org.bukkit.entity.EntityType;
import java.math.BigDecimal; import java.util.List;

/** Immutable, validated shop entry. */
public record ShopItem(String id, Material material, EntityType mob, String name, int slot, BigDecimal buy, BigDecimal sell, String permission, boolean glow, boolean enabled, List<String> lore) {
  public ShopItem { if (buy.signum() < 0 || sell.signum() < 0) throw new IllegalArgumentException("Prices cannot be negative"); }
}
