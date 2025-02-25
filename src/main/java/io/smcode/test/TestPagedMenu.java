package io.smcode.test;

import io.smcode.menu.PagedMenu;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TestPagedMenu extends PagedMenu {
    public TestPagedMenu() {
        super(Rows.FOUR, "Test");
    }

    @Override
    public void onSetItems() {
        final ItemStack[] items = new ItemStack[100];

        for (int i = 0; i < items.length; i++) {
            final ItemStack item = new ItemStack(Material.PAPER);
            final ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text(i));
            item.setItemMeta(meta);
            items[i] = item;
        }

        addAll(items);
    }
}
