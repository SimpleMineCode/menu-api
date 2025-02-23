package io.smcode.menu;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class SimpleMenu implements Menu {
    private static final ItemStack PLACEHOLDER_ITEM;

    static {
        PLACEHOLDER_ITEM = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        final ItemMeta meta = PLACEHOLDER_ITEM.getItemMeta();
        meta.displayName(Component.space());
        PLACEHOLDER_ITEM.setItemMeta(meta);
    }

    private final Map<Integer, Consumer<Player>> actions = new HashMap<>();
    private final Inventory inventory;
    private boolean usePlaceholders;

    public SimpleMenu(Rows rows, String title) {
        this.usePlaceholders = true;
        this.inventory = Bukkit.createInventory(this, rows.getSize(), Component.text(title));
    }

    @Override
    public void click(Player player, int slot) {
        final Consumer<Player> action = this.actions.get(slot);

        if (action != null)
            action.accept(player);
    }

    @Override
    public void setItem(int slot, ItemStack item) {
        setItem(slot, item, player -> {});
    }

    @Override
    public void setItem(int slot, ItemStack item, Consumer<Player> action) {
        this.actions.put(slot, action);
        getInventory().setItem(slot, item);
    }

    public void setUsePlaceholders(boolean usePlaceholders) {
        this.usePlaceholders = usePlaceholders;
    }

    @Override
    public void setPlaceholders() {
        for (int i = 0; i < getInventory().getSize(); i++) {
            getInventory().setItem(i, PLACEHOLDER_ITEM);
        }
    }

    @Override
    public boolean usePlaceholders() {
        return usePlaceholders;
    }

    public abstract void onSetItems();

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    public enum Rows {
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5);

        private final int size;

        Rows(int rows) {
            this.size = rows * 9;
        }

        public int getSize() {
            return size;
        }
    }
}
