package io.smcode.listeners;

import io.smcode.menu.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        final Inventory clickedInventory = event.getClickedInventory();

        if (clickedInventory == null)
            return;

        if (!(clickedInventory.getHolder() instanceof final Menu menu)) {
            // Here if clicked inventory is not a menu
            return;
        }

        // Here if clicked inventory is one of our custom GUIs
        event.setCancelled(true);
        menu.click((Player) event.getWhoClicked(), event.getSlot());
    }
}
