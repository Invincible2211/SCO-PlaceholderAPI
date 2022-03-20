package de.fynn.sco.placeholderapi.placeholder;

import org.bukkit.entity.Player;

/**
 * @author Freddyblitz
 * @version 1.0
 */
public class PlayerPlaceholder extends Placeholder {

    /*--------------------------------------------KONSTRUKTOREN-------------------------------------------------------*/

    /**
     * Der Konstruktor uebergibt der Superklasse den Identifier %player_name%
     */
    public PlayerPlaceholder() {
        super("%player_name%");
    }

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    /**
     * Die Methode gibt den Namen des ubergebene Spielers zurueck
     * @param player Der Spieler, dessen Name verwendet werden soll
     * @return Der Name des uebergebenen Spielers
     */
    public String getPlaceholder(Player player) {
        return player.getName();
    }

}
