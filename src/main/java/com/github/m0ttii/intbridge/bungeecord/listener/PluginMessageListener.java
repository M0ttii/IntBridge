package com.github.m0ttii.intbridge.bungeecord.listener;

import com.github.m0ttii.intbridge.bungeecord.manager.ViolationManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * Created by Adrian D. on 01.06.2018.
 */
public class PluginMessageListener implements Listener {


    public void onMessage(PluginMessageEvent event) throws IOException {
        if (event.getTag().equals("BungeeCord")) {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(event.getData()));
            String channel = in.readUTF();
            if (channel.equals("intave-violation")) {
                String message = in.readUTF();

                final String uuid = String.valueOf(UUID.fromString(event.getReceiver().toString()));
                final Integer violation = Integer.valueOf(message);

                final Stream<String> command = ViolationManager
                        .getCommands(ViolationManager.getViolation(UUID.fromString(uuid)), ViolationManager.getViolation(UUID.fromString(uuid)) + violation);

                command.forEachOrdered(s ->
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), s
                                .replace("[player]", ProxyServer.getInstance().getPlayer(UUID.fromString(uuid)).getName())
                                .replace("[server]", ProxyServer.getInstance().getPlayer(UUID.fromString(uuid)).getServer().getInfo().getName())
                                .replace("[violation]", violation.toString()))
                );
                ViolationManager.addViolation(UUID.fromString(uuid), violation);
                ProxyServer.getInstance().broadcast("FLAGGAGAGA");
            }
        }
    }


}
