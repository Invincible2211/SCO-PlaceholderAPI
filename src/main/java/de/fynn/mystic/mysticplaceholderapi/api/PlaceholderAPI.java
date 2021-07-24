package de.fynn.mystic.mysticplaceholderapi.api;

import de.fynn.mystic.mysticplaceholderapi.MysticPlaceholderAPI;
import de.fynn.mystic.mysticplaceholderapi.placeholder.PlaceholderManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderAPI {

    private PlaceholderManager placeholderManager = MysticPlaceholderAPI.getPlaceholderManager();

    public String replacePlaceholder(Player target, String input){
        List<String> identifier = getIdentifiers(input);
        for (String s:
             identifier) {
            input.replaceAll(s,"");
            placeholderManager.replacePlaceholder(s,target,input);
        }

        return null;
    }

    private List<String> getIdentifiers(String message){

    }

}
