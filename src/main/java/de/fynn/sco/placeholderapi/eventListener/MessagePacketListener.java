package de.fynn.sco.placeholderapi.eventListener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import de.fynn.sco.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.util.List;

public class MessagePacketListener {

    private de.fynn.sco.placeholderapi.api.PlaceholderAPI api = de.fynn.sco.placeholderapi.api.PlaceholderAPI.getPlaceholderAPIInstance();

    public MessagePacketListener(PlaceholderAPI placeholderAPI){
        setup(placeholderAPI);
    }

    public void setup(PlaceholderAPI placeholderAPI){
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(placeholderAPI, ListenerPriority.NORMAL, PacketType.Play.Server.CHAT) {

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
