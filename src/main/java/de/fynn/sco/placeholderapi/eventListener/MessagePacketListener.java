package de.fynn.sco.placeholderapi.eventListener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import de.fynn.sco.placeholderapi.PlaceholderAPIPlugin;
import de.fynn.sco.placeholderapi.placeholder.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author Freddyblitz
 * @version 1.0
 */
public class MessagePacketListener {

    /*--------------------------------------------KONSTRUKTOREN-------------------------------------------------------*/

    /**
     * Der Konstruktor fuehrt das Setup des MessageListeners aus.
     */
    public MessagePacketListener(){
        setup();
    }

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    /**
     * Das Setup registriert einen PacketListener bei ProtocolLib um alle Chat Packets abzufangen und automatisch
     * Placeholder zu ersetzen.
     */
    public void setup(){
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(PlaceholderAPIPlugin.getPlugin(), ListenerPriority.NORMAL, PacketType.Play.Server.CHAT) {

            @Override
            public void onPacketSending(PacketEvent event) {

                if (event.getPacketType() == PacketType.Play.Server.CHAT) {

                    PacketContainer packet = event.getPacket();
                    Player player = event.getPlayer();
                    List<WrappedChatComponent> components = packet.getChatComponents().getValues();

                    for (WrappedChatComponent component : components) {
                        component.setJson(PlaceholderAPI.replacePlaceholder(player,component.getJson()));
                        packet.getChatComponents().write(components.indexOf(component), component);
                    }

                }

            }

        });
    }



}
