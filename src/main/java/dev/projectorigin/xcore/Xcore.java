package dev.projectorigin.xcore;

import dev.projectorigin.xcore.items.ItemGiveCommand;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Xcore extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("giveitem")).setExecutor(new ItemGiveCommand());
        Objects.requireNonNull(getCommand("giveitem")).setTabCompleter(new ItemGiveCommand());
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }
}
