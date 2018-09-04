package com.trophonix.dispensersplus;

import com.trophonix.dispensersplus.modules.BlockPlaceModule;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Dispenser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DispensersPlus extends JavaPlugin implements Listener {

  private List<DispenserModule> modules = new ArrayList<>();

  @Override
  public void onEnable() {
    saveDefaultConfig();
    getServer().getPluginManager().registerEvents(this, this);
    Arrays.asList(
            BlockPlaceModule.class
    ).forEach(clazz -> {
      try {
        DispenserModule module = clazz.newInstance();
        if (!getConfig().getBoolean("modules." + module.getId(), true)) return;
        modules.add(module);
      } catch (InstantiationException | IllegalAccessException ignored) { }
    });
  }

  @EventHandler
  public void onActivate(BlockDispenseEvent event) {
    if (event.getBlock().getType() == Material.DISPENSER) {
      Dispenser dispenser = (Dispenser)event.getBlock().getState();
      Inventory inventory = dispenser.getInventory();
      for (DispenserModule module : modules) {
        if (module.getName().equalsIgnoreCase(inventory.getTitle())) {
          module.onActivate(dispenser, event.getItem());
          event.setCancelled(true);
          break;
        }
      }
    }
  }

}
