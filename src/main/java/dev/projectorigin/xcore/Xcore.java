package dev.projectorigin.xcore;

import dev.projectorigin.xcore.hud.TextProcessor;
import dev.projectorigin.xcore.items.ItemGiveCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Xcore extends JavaPlugin {
    private TextProcessor textProcessor;
    @Override
    public void onEnable() {
        // Register the "giveitem" command
        getCommand("giveitem").setExecutor(new ItemGiveCommand());
        getCommand("giveitem").setTabCompleter(new ItemGiveCommand());

    }

    @Override
    public void onDisable() {
        // Any logic to execute when the plugin is disabled
    }
    public TextProcessor getTextProcessor() {
        return textProcessor;
    }





    // the following code is used for ACTIONBAR
    private void noKeyFound(String ...keys) {
        getLogger().warning("Configuration migration: Couldn't find configuration key(s) %s, skipping...".formatted(String.join(", ", keys)));
    }

    private void migrateConfiguration() {
        getLogger().info("Checking for configuration migration...");
        var config = getConfig();
        if (config.getKeys(false).isEmpty()) {
            getLogger().info("Empty configuration, creating...");
            return;
        }
        if (config.isInt("config-version")) {
            getLogger().info("Configuration key config-version exists, assuming there's no need for migration :)");
            return;
        }

        if (config.isString("font") && config.isString("special-symbol-for-scoreboards")) {
            config.set("fonts.default-font.name", config.get("font"));
            config.set("fonts.default-font.special-symbol", config.get("special-symbol-for-scoreboards"));
        } else noKeyFound("font", "special-symbol-for-scoreboards");
        var regex = config.get("regex");
        config.set("regex", null);
        if (regex != null && config.isBoolean("invert-regex")) {
            config.set("regex.value", regex);
            config.set("regex.invert", config.get("invert-regex"));
        } else noKeyFound("font", "invert-regex");
        config.set("packets", null);
        config.set("invert-regex", null);
        config.set("font", null);
        config.set("special-symbol-for-scoreboards", null);
        config.set("config-version", 1);
        getLogger().info("Successfully migrated to a new configuration!");
        saveConfig();
    }

    private void setupConfiguration() {
        var config = getConfig();
        if (!config.isConfigurationSection("fonts")) {
            config.addDefault("fonts.default-font.name", "namespace:key");
            config.addDefault("fonts.default-font.special-symbol", "$u");
        }
        config.addDefault("regex.value", "[\\p{Print}&&[^~,],]+");
        config.addDefault("regex.invert", false);
        for (var key : List.of("boss-bar", "action-bar", "scoreboard-title", "scoreboard-scores")) {
            config.addDefault("packets.%s.enable".formatted(key), true);
            config.addDefault("packets.%s.forced-font".formatted(key), key.equals("scoreboard-scores") ? "" : "default-font");
        }
        config.addDefault("config-version", 1);
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
