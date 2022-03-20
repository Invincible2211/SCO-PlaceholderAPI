package de.fynn.sco.placeholderapi.placeholder;

import org.bukkit.entity.Player;

/**
 * @author Freddyblitz
 * @version 1.0
 */
public class PlaceholderAPI {

    /*----------------------------------------------ATTRIBUTE---------------------------------------------------------*/

    private final static PlaceholderManager placeholderManager = PlaceholderManager.getInstance();

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    /**
     * Diese Methode registriert einen neuen Placeholder in der PlaceholderAPI.
     * @param placeholder Eine Klasse die von Placeholder erbt
     * @return Wahrheitswert, ob der Placeholder registriert werden konnte
     * -> true wenn der Placeholder erfolgreich registriert werden konnte
     * -> false wenn der Placeholder schon registriert wurde
     */
    public static boolean addPlaceholder(Placeholder placeholder){
        return placeholderManager.addPlaceholder(placeholder);
    }

    /**
     * Diese Methode ersetzt alle Placeholder in einem String.
     * @param target Optional: Ein Spieler, der benoetigt wird, um die Placeholder zu ersetzen
     * @param message Der String, in der die Placeholder ersetzt werden sollen
     * @return Den uebergebenen String mit ersetzten Placeholdern
     */
    public static String replacePlaceholder(Player target, String message){
        return placeholderManager.replacePlaceholder(target, message);
    }

}
