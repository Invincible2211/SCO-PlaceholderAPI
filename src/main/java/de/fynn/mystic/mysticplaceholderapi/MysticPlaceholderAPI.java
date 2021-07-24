package de.fynn.mystic.mysticplaceholderapi;

import de.fynn.mystic.mysticplaceholderapi.placeholder.Placeholder;
import de.fynn.mystic.mysticplaceholderapi.placeholder.PlaceholderManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class MysticPlaceholderAPI extends JavaPlugin {

    private static PlaceholderManager placeholderManager;

    @Override
    public void onEnable() {
        this.getLogger().info("Enabling MysticPlaceholderAPI");
        placeholderManager = new PlaceholderManager();
    }

    @Override
    public void onDisable() {

    }

    public static PlaceholderManager getPlaceholderManager() {
        return placeholderManager;
    }

}
