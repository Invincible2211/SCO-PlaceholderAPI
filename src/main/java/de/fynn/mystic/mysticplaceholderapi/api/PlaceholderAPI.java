package de.fynn.mystic.mysticplaceholderapi.api;

import de.fynn.mystic.mysticplaceholderapi.MysticPlaceholderAPI;
import de.fynn.mystic.mysticplaceholderapi.placeholder.PlaceholderManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderAPI {

    private PlaceholderManager placeholderManager = MysticPlaceholderAPI.getPlaceholderManager();
    private static PlaceholderAPI placeholderAPI;

    {
        placeholderAPI = this;
    }

    public String replacePlaceholder(Player target, String message){
        List<String> identifier = getIdentifiers(message);
        for (String s:
             identifier) {
            message = message.replaceAll(s,"");
            message = placeholderManager.replacePlaceholder(s,target, message);
        }
        return message;
    }

    private List<String> getIdentifiers(String message){
        List<String> availableIdentifier = placeholderManager.getAvailableIdentifiers();
        List<String> usedIdentifiers = new ArrayList<>();
        for (String s:
             availableIdentifier) {
            if (message.contains(s))usedIdentifiers.add(s);
        }
        return usedIdentifiers;
    }

    public static PlaceholderAPI getPlaceholderAPIInstance() {
        return placeholderAPI;
    }

}
