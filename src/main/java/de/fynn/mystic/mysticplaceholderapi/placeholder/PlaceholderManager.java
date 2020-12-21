package de.fynn.mystic.mysticplaceholderapi.placeholder;

import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlaceholderManager {

    private static final HashMap<String,Placeholder> placeholders = new HashMap<>();
    private static final HashMap<String, List<String>> placeholderFromPlugin = new HashMap<>();
    private final Plugin parent;

    /**
     * the constructor needs the plugin that sets up the hook
     * @param parent the plugin that create the hook
     */
    public PlaceholderManager(Plugin parent){
        this.parent = parent;
    }

    /**
     * the method adds a new placeholder to the placeholders of the parent plugin
     * @param placeholderAsString the name of the placeholder, e.g. %player_name%
     * @param placeholder an object that implements the placeholder interface
     */
    public void registerPlaceholder(String placeholderAsString,Placeholder placeholder){
        placeholders.put(parent.getName().toLowerCase()+"."+placeholderAsString, placeholder);
        List<String> placeholdersList = placeholderFromPlugin.get(parent.getName());
        if(placeholdersList==null)placeholdersList = new ArrayList<>();
        placeholdersList.add(placeholderAsString);
        placeholderFromPlugin.put(parent.getName(),placeholdersList);
    }

    /**
     * this method replaces all placeholders of the passed string and replaces them with the correct values.
     * Only placeholders from the parent plugin are used.
     * @param msg the message that contains the placeholder
     * @param values the required objects to replace the placeholder,
     *               for the placeholder %player_name% this could be a player object,
     *               but no value has to be passed
     * @return the passed message with replaced placeholders (only from the parent plugin)
     */
    @SafeVarargs
    public final <T> String getPlaceholder(String msg, T... values){
        for (String placeholder:
             placeholderFromPlugin.get(parent.getName())) {
            if(msg.contains(placeholder)){
                return msg.replace(placeholder,placeholders.get(parent.getName().toLowerCase()+"."+placeholder).getPlaceholder(values));
            }
        }
        return msg;
    }

    /**
     * this method replaces all placeholders of the passed string and replaces them with the correct values.
     * Only placeholders from the chosen plugin are used.
     * @param plugin the plugin whose placeholders should be used
     * @param msg the message that contains the placeholder
     * @param values the required objects to replace the placeholder,
     * for the placeholder %player_name% this could be a player object,
     * but no value has to be passed
     * @return the passed message with replaced placeholders (only from the parent plugin)
     */
    @SafeVarargs
    public final <T> String getPlaceholder(String plugin, String msg, T... values){
        for (String placeholder:
                placeholderFromPlugin.get(plugin)) {
            if(msg.contains(placeholder)){
                return msg.replace(placeholder, placeholders.get(plugin.toLowerCase()+"."+placeholder).getPlaceholder(values));
            }
        }
        return msg;
    }

}