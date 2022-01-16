package de.fynn.mystic.mysticplaceholderapi.placeholder;

import org.bukkit.entity.Player;

public class DefaultPlaceholders extends Placeholder {

    public DefaultPlaceholders() {
        super(new String[]{"%player_name"});
    }

    @Override
    public String getPlaceholder(Player player, String message) {
        return message.replaceAll("%player_name%",player.getName());
    }

}
