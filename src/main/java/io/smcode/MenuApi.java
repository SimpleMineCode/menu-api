package io.smcode;

import io.smcode.commands.KillCommand;
import io.smcode.listeners.InventoryListener;
import org.bukkit.plugin.java.JavaPlugin;

public class MenuApi extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        getCommand("killgui").setExecutor(new KillCommand());
    }
}
