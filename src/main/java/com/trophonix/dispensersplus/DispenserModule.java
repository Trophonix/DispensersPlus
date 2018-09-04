package com.trophonix.dispensersplus;

import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface DispenserModule {

  /**
   * Called when a dispenser of this type is activated
   * @param block The dispenser block
   * @return Whether the activation was successful/meaningful
   */
  boolean onActivate(Dispenser block, ItemStack item);

  String getId();
  String getName();

}
