package io.smcode;

import io.smcode.test.KillMenu;
import org.bukkit.Material;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.PlayerMock;
import org.mockbukkit.mockbukkit.inventory.ItemStackMock;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class TestMenu {
    private ServerMock server;
    private PlayerMock player;

    @BeforeEach
    void setup() {
        this.server = MockBukkit.mock();
        this.player = server.addPlayer("SMCode");
    }

    @AfterEach
    void clear() {
        MockBukkit.unmock();
    }

    @Test
    void testMenuItems() {
        var menu = new KillMenu();
        menu.setItem(1, ItemStackMock.of(Material.STONE_BUTTON));

        assertNotNull(menu.getInventory().getItem(1));
        assertEquals(Material.STONE_BUTTON, Objects.requireNonNull(menu.getInventory().getItem(1)).getType());
    }

    @Test
    void testMenuOpen() {
        var menu = new KillMenu();
        menu.open(player);
        var inventory = player.getOpenInventory().getTopInventory();

        assertNotNull(inventory);
        assertEquals(menu, inventory.getHolder());
    }
}
