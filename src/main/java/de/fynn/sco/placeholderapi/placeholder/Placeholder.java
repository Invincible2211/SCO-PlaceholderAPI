package de.fynn.sco.placeholderapi.placeholder;

import org.bukkit.entity.Player;

/**
 * @author Freddyblitz
 * @version 1.0
 */
public abstract class Placeholder {

    /*----------------------------------------------ATTRIBUTE---------------------------------------------------------*/

    private final String identifier;

    /*--------------------------------------------KONSTRUKTOREN-------------------------------------------------------*/

    /**
     * Der Konstruktor benoetigt einen Identifier fuer den Placeholder, Bsp.: %player_name%.
     * @param identifier Der Identifier des Placeholders
     */
    public Placeholder(String identifier){
        this.identifier = identifier;
    }

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    /**
     * Es muss eine getPlaceholder(Player) Methode implementiert werden, die, wenn der Identifier in einem String vorkommt
     * zurueckgibt, durch was der Identifier ersetzt werden soll.
     * @param player Der Spieler fuer den der Placeholder aufgerufen wird, kann aber auch null sein, wenn kein Spieler
     *               uebergeben wird.
     * @return Der String, der den Identifier ersetzt
     */
    public abstract String getPlaceholder(Player player);

    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/

    public String getIdentifier() {
        return identifier;
    }

}
