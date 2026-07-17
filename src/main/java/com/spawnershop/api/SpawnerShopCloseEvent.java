package com.spawnershop.api;
import org.bukkit.entity.Player; import org.bukkit.event.Event; import org.bukkit.event.HandlerList;
/** Fired after a player closes a SpawnerShop inventory. */
public final class SpawnerShopCloseEvent extends Event { private static final HandlerList HANDLERS=new HandlerList(); private final Player player; public SpawnerShopCloseEvent(Player player){this.player=player;} public Player getPlayer(){return player;} @Override public HandlerList getHandlers(){return HANDLERS;} public static HandlerList getHandlerList(){return HANDLERS;} }
