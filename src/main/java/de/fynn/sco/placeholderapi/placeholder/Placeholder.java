package de.fynn.sco.placeholderapi.placeholder;

import de.fynn.sco.placeholderapi.utils.PlaceholderHook;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

@PlaceholderHook
public abstract class Placeholder {

    private final List<String> placeholders;

    public Placeholder(String[] placeholders){
        this.placeholders = Arrays.asList(placeholders);
    }

    public abstract String getPlaceholder(Player player, String message);

    protected List<String> getPlaceholders(){
        return placeholders;
    }

}
