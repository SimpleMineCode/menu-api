package io.smcode;

import io.smcode.commands.KillCommand;
import io.smcode.listeners.InventoryListener;
import org.bukkit.plugin.java.JavaPlugin;

public class MenuApi extends JavaPlugin {
    @Override
    public void onEnable() {
        setUp(this);
        getCommand("killgui").setExecutor(new KillCommand());
    }

    public static void setUp(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new InventoryListener(), plugin);
    }
}
