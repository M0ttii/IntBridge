package com.github.m0ttii.intbridge.bungeecord.listener;

import com.github.m0ttii.intbridge.bungeecord.IntBridge;
import com.github.m0ttii.intbridge.bungeecord.manager.ViolationManager;
import net.md_5.bungee.api.ProxyServer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.UUID;
import java.util.stream.Stream;

/**
 * Created by Adrian D. on 01.06.2018.
 */
public class PubSubListener {
    Jedis jSubscriber;
    public PubSubListener()
    {
        this.jSubscriber = IntBridge.getInstance().getJedis();
        jSubscriber.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                final String[] args = message.split(":");

                final String uuid = args[0];
                final Integer violation = Integer.valueOf(args[1]);

                final Stream<String> command = ViolationManager
                        .getCommands(ViolationManager.getViolation(UUID.fromString(uuid)), ViolationManager.getViolation(UUID.fromString(uuid)) + violation);

                command.forEachOrdered(s ->
                    ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), s
                            .replace("[player]", ProxyServer.getInstance().getPlayer(UUID.fromString(uuid)).getName())
                            .replace("[server]", ProxyServer.getInstance().getPlayer(UUID.fromString(uuid)).getServer().getInfo().getName())
                            .replace("[violation]", violation.toString()))
                );
                ViolationManager.addViolation(UUID.fromString(uuid), violation);

            }
        }, "intave-violation");
    }


}
