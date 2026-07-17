package com.spawnershop.command;

import com.spawnershop.SpawnerShopPlugin;
import com.spawnershop.util.Text;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import java.util.List;
import java.util.Locale;

/** Handles the shop and bulk normal-item sell commands. */
public final class ShopCommand implements CommandExecutor, TabCompleter {
  private final SpawnerShopPlugin plugin;
  public ShopCommand(SpawnerShopPlugin plugin) { this.plugin = plugin; }
  @Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (command.getName().equalsIgnoreCase("sell")) {
      if (sender instanceof Player player) plugin.transactions().sellConfiguredItems(player, plugin.sellPrices().prices());
      else sender.sendMessage("Players only.");
      return true;
    }
    if (args.length == 0) { if (sender instanceof Player player) plugin.view().open(player); else sender.sendMessage("Players only."); return true; }
    switch (args[0].toLowerCase(Locale.ROOT)) {
      case "reload" -> { if (!sender.hasPermission("spawnershop.reload")) no(sender); else { plugin.reloadPlugin(); sender.sendMessage(Text.color("&aSpawnerShop configuration reloaded.")); } }
      case "open" -> { if (!sender.hasPermission("spawnershop.admin")) no(sender); else if (args.length < 2) sender.sendMessage("/spawnershop open <player>"); else { Player target = Bukkit.getPlayerExact(args[1]); if (target == null) sender.sendMessage("Player not found."); else plugin.view().open(target); } }
      default -> sender.sendMessage(Text.color("&e/spawnershop [reload|open <player>|editor|debug]"));
    }
    return true;
  }
  private void no(CommandSender sender) { sender.sendMessage(Text.color("&cYou do not have permission.")); }
  @Override public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
    if (command.getName().equalsIgnoreCase("sell")) return List.of();
    if (args.length == 1) return List.of("reload", "open", "editor", "debug").stream().filter(x -> x.startsWith(args[0].toLowerCase(Locale.ROOT))).toList();
    if (args.length == 2 && args[0].equalsIgnoreCase("open")) return Bukkit.getOnlinePlayers().stream().map(Player::getName).filter(x -> x.toLowerCase(Locale.ROOT).startsWith(args[1].toLowerCase(Locale.ROOT))).toList();
    return List.of();
  }
}
