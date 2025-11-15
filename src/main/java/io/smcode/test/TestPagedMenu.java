package io.smcode.test;

import io.smcode.menu.PagedMenu;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class TestPagedMenu extends PagedMenu {
    public TestPagedMenu() {
        super(Rows.FOUR, Component.text("Test"));
    }

    @Override
    public void onSetItems() {
        final Map<ItemStack, Consumer<Player>> items = new LinkedHashMap<>();

        for (int i = 0; i < 100; i++) {
            final ItemStack item = new ItemStack(Material.PAPER);
            final ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text(i));
            item.setItemMeta(meta);
            int index = i;
            items.put(item, (player) -> player.sendMessage(Component.text("Click on item " + index)));
        }

        addAll(items);
    }
}
