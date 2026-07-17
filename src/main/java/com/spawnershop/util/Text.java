package com.spawnershop.util;
import org.bukkit.ChatColor; import java.util.List;
public final class Text { private Text(){} public static String color(String s){return ChatColor.translateAlternateColorCodes('&',s==null?"":s);} public static List<String> color(List<String> s){return s.stream().map(Text::color).toList();} }
