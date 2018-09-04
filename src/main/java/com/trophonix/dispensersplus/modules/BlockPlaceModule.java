package com.trophonix.dispensersplus.modules;

import com.trophonix.dispensersplus.DispenserModule;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Dispenser;
import org.bukkit.inventory.ItemStack;

public class BlockPlaceModule implements DispenserModule {

  @Override
  public boolean onActivate(Dispenser block, ItemStack item) {
    org.bukkit.material.Dispenser material = (org.bukkit.material.Dispenser)block.getBlock().getState().getData();
    BlockFace facing = material.getFacing();
    Block front = block.getBlock().getRelative(facing);
    if (front.getType() != null && front.getType() != Material.AIR) {
      return false;
    }
    if (item != null && item.getType() != null) {
      if (item.getType().isBlock()) {
        front.setType(item.getType());
      } else {
        return false;
      }
    }
    return true;
  }

  @Override
  public String getId() {
    return "blockPlaceModule";
  }

  @Override
  public String getName() {
    return ChatColor.YELLOW + "Block Placer";
  }

}
