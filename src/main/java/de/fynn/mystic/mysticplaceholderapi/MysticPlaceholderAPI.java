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
        placeholderManager = new PlaceholderManager(this);
        init();
    }

    @Override
    public void onDisable() {

    }

    private void init(){
        placeholderManager.registerPlaceholder("%player_name%", new Placeholder() {
            @Override
            public <T> String getPlaceholder(T... values) {
                return ((Player)values[0]).getDisplayName();
            }
        });
        placeholderManager.registerPlaceholder("%time%", new Placeholder() {
            @Override
            public <T> String getPlaceholder(T... values) {
                SimpleDateFormat format = new SimpleDateFormat("hh:mm");
                return format.format(new Date());
            }
        });
    }

    public static <T> String getDefaultPlaceholder(String msg,T... values){
        return placeholderManager.getPlaceholder(msg,values);
    }

}
