package io.smcode.test;

import io.smcode.menu.SimpleMenu;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KillMenu extends SimpleMenu {
    public KillMenu() {
        super(Rows.THREE, "End your suffering");
    }

    @Override
    public void onSetItems() {
        final ItemStack killItem = new ItemStack(Material.BARRIER);
        final ItemMeta meta = killItem.getItemMeta();
        meta.displayName(Component.text("Kill yourself", NamedTextColor.RED));
        killItem.setItemMeta(meta);

        setItem(13, killItem, player -> {
            // This will be executed when the player clicks this item
            player.sendRichMessage("<red>You killed yourself");
            player.setHealth(0);
        });
    }
}
