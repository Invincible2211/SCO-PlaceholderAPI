package de.fynn.mystic.mysticplaceholderapi.placeholder;

import de.fynn.mystic.mysticplaceholderapi.utils.AnnotationController;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlaceholderManager {

    private static HashMap<String,HashMap<List<String>,Placeholder>> placeholders;

    {
        loadPlaceholders();
    }

    private void loadPlaceholders(){
        AnnotationController controller = new AnnotationController();
        try {
            placeholders = convertClassFiles(controller.getPlaceholderClasses());
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String,HashMap<List<String>,Placeholder>> convertClassFiles(HashMap<String,List<Class<Placeholder>>> classHashmap) throws IllegalAccessException, InstantiationException {
        HashMap<String, HashMap<List<String>, Placeholder>> castedClasses = new HashMap<>();
        List<String> keys = new ArrayList<>(classHashmap.keySet());
        for (int i = 0; i < classHashmap.size(); i++) {
            HashMap<List<String>, Placeholder> listPlaceholderHashMap = new HashMap<>();
            List<Class<Placeholder>> placeholderClassList = classHashmap.get(keys.get(i));
            for (Class<Placeholder> cls :
                    placeholderClassList) {
                Placeholder placeholder = cls.newInstance();
                List<String> placeholderList = placeholder.getPlaceholders();
                listPlaceholderHashMap.put(placeholderList, placeholder);
            }
            castedClasses.put("*"+keys.get(i)+"*", listPlaceholderHashMap);
        }
        return castedClasses;
    }

    public String replacePlaceholder(String identifier, Player player, String message){
        List<Placeholder> usedPlaceholders = locatePlaceholders(placeholders.get(identifier),message);
        for (Placeholder p:
             usedPlaceholders) {
            message = p.getPlaceholder(player, message);
        }
        return message;
    }

    private List<Placeholder> locatePlaceholders(HashMap<List<String>,Placeholder> data, String message){
        List<Placeholder> locatedPlaceholders = new ArrayList<>();
        List<List<String>> keyList =  new ArrayList<>(data.keySet());
        for (List<String> placeholderList:
             keyList) {
            for (String placeolder:
                 placeholderList) {
                if(message.contains(placeolder)){
                    locatedPlaceholders.add(data.get(placeholderList));
                }
            }
        }
        return locatedPlaceholders;
    }

    public List<String> getAvailableIdentifiers(){

    }

}