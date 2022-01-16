package de.fynn.sco.placeholderapi;

import de.fynn.sco.placeholderapi.eventListener.MessagePacketListener;
import de.fynn.sco.placeholderapi.placeholder.PlaceholderManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlaceholderAPI extends JavaPlugin {

    private static PlaceholderManager placeholderManager;

    @Override
    public void onEnable() {
        placeholderManager = new PlaceholderManager();
        new de.fynn.sco.placeholderapi.api.PlaceholderAPI();
        new MessagePacketListener(this);
    }

    @Override
    public void onDisable() {

    }

    public static PlaceholderManager getPlaceholderManager() {
        return placeholderManager;
    }

}
