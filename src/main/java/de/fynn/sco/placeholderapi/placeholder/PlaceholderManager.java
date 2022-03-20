package de.fynn.sco.placeholderapi.placeholder;

import de.fynn.sco.placeholderapi.eventListener.MessagePacketListener;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;

/**
 * @author Freddyblitz
 * @version 1.0
 */
public class PlaceholderManager {

    /*----------------------------------------------ATTRIBUTE---------------------------------------------------------*/

    private static final PlaceholderManager instance = new PlaceholderManager();

    private final HashMap<String, Placeholder> placeholderHashMap = new HashMap<>();

    /*--------------------------------------------KONSTRUKTOREN-------------------------------------------------------*/

    /**
     * Der Konstruktor erstellt eine neue Instanz des MessagePacketListeners, welche fuer das automatische Ersetzen
     * von Placeholdern in Nachrichten verwendet wird
     */
    private PlaceholderManager (){
        new MessagePacketListener();
    }

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    /**
     * Diese Methode fuegt der API einen neuen Placeholder hinzu.
     * @param placeholder Der Placeholder der hinzugefuegt werden soll
     * @return Wahrheitswert, ob der Placeholder hinzugefuegt werden konnte;
     * -> true wenn der Placeholder hinzugefuegt werden konnte
     * -> false wenn der Placeholder schon registriert wurde
     */
    public boolean addPlaceholder(Placeholder placeholder){
        if (placeholderHashMap.containsKey(placeholder.getIdentifier())){
            return false;
        } else {
            placeholderHashMap.put(placeholder.getIdentifier(), placeholder);
            return true;
        }
    }

    /**
     * Diese Methode ersetzt alle Placeholder in einem String.
     * @param player Optional: Ein Spieler, der benoetigt wird, um die Placeholder zu ersetzen
     * @param message Der String, in der die Placeholder ersetzt werden sollen
     * @return Den uebergebenen String mit ersetzten Placeholdern
     */
    public String replacePlaceholder(Player player, String message){
        Collection<String> identifierList = placeholderHashMap.keySet();
        for (String placeholder:
             identifierList) {
            if (message.contains(placeholder)){
                message = message.replaceAll(placeholder, this.placeholderHashMap.get(placeholder).getPlaceholder(player));
            }
        }
        return message;
    }

    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/

    protected static PlaceholderManager getInstance() {
        return instance;
    }

}