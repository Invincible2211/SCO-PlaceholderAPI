package de.fynn.sco.placeholderapi;

import de.fynn.sco.placeholderapi.placeholder.PlaceholderAPI;
import de.fynn.sco.placeholderapi.placeholder.PlayerPlaceholder;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Freddyblitz
 * @version 1.0
 */
public final class PlaceholderAPIPlugin extends JavaPlugin {

    /*----------------------------------------------ATTRIBUTE---------------------------------------------------------*/

    private static Plugin plugin;

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    /**
     * Diese Methode wird beim laden des Plugins ausgefuehrt
     */
    @Override
    public void onEnable() {
        plugin = this;
        PlaceholderAPI.addPlaceholder(new PlayerPlaceholder());
    }

    /**
     * Diese Methode wird beim deaktivieren des Plugins ausgefuehrt
     */
    @Override
    public void onDisable() {

    }

    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/

    public static Plugin getPlugin() {
        return plugin;
    }

}
