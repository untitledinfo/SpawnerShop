package com.spawnershop.api;

import com.spawnershop.config.ShopItem;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import java.math.BigDecimal;

/** Fired before a configured item's dynamic price is changed. */
public final class SpawnerPriceChangeEvent extends Event {
  private static final HandlerList HANDLERS = new HandlerList();
  private final ShopItem item; private final BigDecimal oldPrice; private final BigDecimal newPrice;
  public SpawnerPriceChangeEvent(ShopItem item, BigDecimal oldPrice, BigDecimal newPrice) { this.item=item; this.oldPrice=oldPrice; this.newPrice=newPrice; }
  public ShopItem getItem(){ return item; } public BigDecimal getOldPrice(){ return oldPrice; } public BigDecimal getNewPrice(){ return newPrice; }
  @Override public HandlerList getHandlers(){ return HANDLERS; } public static HandlerList getHandlerList(){ return HANDLERS; }
}
