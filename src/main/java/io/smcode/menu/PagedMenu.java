package io.smcode.menu;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;
import java.util.function.Consumer;

public abstract class PagedMenu extends SimpleMenu {
    private int currentPage = 0;
    private int maxPage = 0;

    public PagedMenu(Rows rows, Component title) {
        super(rows, title);
        setNavigation();
    }

    protected void setNavigation() {
        setItem(getInventory().getSize() - 1, getItemNextPage(), player -> {
            currentPage = Math.min(maxPage, currentPage + 1);
            update();
        });
        setItem(getInventory().getSize() - 9, getItemPreviousPage(), player -> {
            currentPage = Math.max(0, currentPage - 1);
            update();
        });
    }

    public void addAll(ItemStack... items) {
        final int safeArea = getInventory().getSize() - 9;

        for (int i = 0; i < items.length; i++) {
            final int page = i / safeArea;
            final int slot = i % safeArea;

            setItem(page, slot, items[i]);
        }
    }

    public void addAll(Map<ItemStack, Consumer<Player>> entries) {
        final int safeArea = getInventory().getSize() - 9;

        int index = 0;
        for (Map.Entry<ItemStack, Consumer<Player>> entry : entries.entrySet()) {
            int page = index / safeArea;
            int slot = index % safeArea;

            ItemStack item = entry.getKey();
            Consumer<Player> action = entry.getValue();

            setItem(page, slot, item, action);

            index++;
        }
    }

    @Override
    public void update() {
        getInventory().clear();

        for (int i = 0; i < getInventory().getSize(); i++) {
            final int index = currentPage * getInventory().getSize() + i;
            final ItemStack item = this.getItemsMap().get(index);

            if (item != null)
                getInventory().setItem(i, item);
        }

        setPlaceholders();
        setNavigation();
    }

    public void setItem(int page, int slot, ItemStack item) {
        setItem(page, slot, item, player -> {});
    }

    public void setItem(int page, int slot, ItemStack item, Consumer<Player> action) {
        final int index = page * getInventory().getSize() + slot;
        getActionsMap().put(index, action);
        getItemsMap().put(index, item);

        if (page == 0)
            getInventory().setItem(index, item);

        if (page > maxPage)
            maxPage = page;
    }

    @Override
    public void setPlaceholders() {
        for (int i = 0; i < getInventory().getSize() - 9; i++) {
            if (getInventory().getItem(i) == null)
                getInventory().setItem(i, PLACEHOLDER_ITEM);
        }
    }

    public ItemStack getItemPreviousPage() {
        final ItemStack item = new ItemStack(Material.STONE_BUTTON);
        final ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("Previous page", NamedTextColor.GOLD));
        item.setItemMeta(meta);

        return item;
    }

    public ItemStack getItemNextPage() {
        final ItemStack item = new ItemStack(Material.STONE_BUTTON);
        final ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("Next page", NamedTextColor.GOLD));
        item.setItemMeta(meta);

        return item;
    };
}
