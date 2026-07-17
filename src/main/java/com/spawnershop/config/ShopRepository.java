package com.spawnershop.config;
import com.spawnershop.util.Text;
import org.bukkit.Material; import org.bukkit.configuration.ConfigurationSection; import org.bukkit.configuration.file.YamlConfiguration; import org.bukkit.entity.EntityType; import org.bukkit.plugin.Plugin;
import java.io.File; import java.math.BigDecimal; import java.util.*;

public final class ShopRepository {
 private final Plugin plugin; private volatile Map<Integer, ShopItem> slots = Map.of(); private volatile Map<String, ShopItem> ids = Map.of();
 public ShopRepository(Plugin plugin) { this.plugin=plugin; }
 public void reload() { File file=new File(plugin.getDataFolder(),"shops.yml"); YamlConfiguration y=YamlConfiguration.loadConfiguration(file); Map<Integer,ShopItem> bySlot=new HashMap<>(); Map<String,ShopItem> byId=new HashMap<>(); ConfigurationSection root=y.getConfigurationSection("items"); if(root!=null) for(String id:root.getKeys(false)) try { ConfigurationSection s=root.getConfigurationSection(id); if(s==null) continue; int slot=s.getInt("slot", -1); Material material=Material.matchMaterial(s.getString("material","STONE")); if(material==null||slot<0||slot>53) throw new IllegalArgumentException("invalid material or slot"); EntityType mob=null; String type=s.getString("mob",""); if(!type.isBlank()) mob=EntityType.valueOf(type.toUpperCase(Locale.ROOT)); ShopItem item=new ShopItem(id,material,mob,s.getString("name",id),slot,new BigDecimal(s.getString("buy","0")),new BigDecimal(s.getString("sell","0")),s.getString("permission",""),s.getBoolean("glow"),s.getBoolean("enabled",true),s.getStringList("lore")); if(bySlot.put(slot,item)!=null) throw new IllegalArgumentException("duplicate slot"); byId.put(id,item); } catch(Exception e) { plugin.getLogger().warning("Ignoring shops.yml item '"+id+"': "+e.getMessage()); }
 slots=Map.copyOf(bySlot); ids=Map.copyOf(byId); }
 public Map<Integer,ShopItem> items(){return slots;} public Optional<ShopItem> itemAt(int slot){return Optional.ofNullable(slots.get(slot));} public Collection<ShopItem> all(){return ids.values();}
 public boolean isSupportedIntegration(String name) { return Set.of("PlaceholderAPI","LuckPerms","Essentials","CMI","DecentHolograms","SilkSpawners","MineableSpawners","RoseStacker","WildStacker","ItemsAdder","Oraxen","MMOItems","EconomyShopGUI","Citizens","ProtocolLib").contains(name); }
}
