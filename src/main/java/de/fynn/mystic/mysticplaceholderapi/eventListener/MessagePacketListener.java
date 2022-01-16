package de.fynn.mystic.mysticplaceholderapi.eventListener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import de.fynn.mystic.mysticplaceholderapi.MysticPlaceholderAPI;
import de.fynn.mystic.mysticplaceholderapi.api.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.util.List;

public class MessagePacketListener {

    private PlaceholderAPI api = PlaceholderAPI.getPlaceholderAPIInstance();

    public MessagePacketListener(MysticPlaceholderAPI mysticPlaceholderAPI){
        setup(mysticPlaceholderAPI);
    }

    public void setup(MysticPlaceholderAPI mysticPlaceholderAPI){
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(mysticPlaceholderAPI, ListenerPriority.NORMAL, PacketType.Play.Server.CHAT) {

            @Override
            public void onPacketSending(PacketEvent event) {

                if (event.getPacketType() == PacketType.Play.Server.CHAT) {

                    PacketContainer packet = event.getPacket();
                    Player player = event.getPlayer();
                    List<WrappedChatComponent> components = packet.getChatComponents().getValues();

                    for (WrappedChatComponent component : components) {
                        component.setJson(api.replacePlaceholder(player,component.getJson()));
                        packet.getChatComponents().write(components.indexOf(component), component);
                    }

                }

            }

        });
    }



}
