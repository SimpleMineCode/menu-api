package io.smcode.menu;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.function.Consumer;

public abstract class PagedMenu extends SimpleMenu {
    private int currentPage = 0;
    private int maxPage = 0;

    public PagedMenu(Rows rows, String title) {
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
        for (int i = 0; i < items.length; i++) {
            final int page = i / (getInventory().getSize() - 9);
            final int slot = i % (getInventory().getSize() - 9);

            setItem(page, slot, items[i]);
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
