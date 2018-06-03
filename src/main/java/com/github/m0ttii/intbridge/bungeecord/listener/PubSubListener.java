package com.github.m0ttii.intbridge.bungeecord.listener;

import com.github.m0ttii.intbridge.bungeecord.manager.ViolationManager;
import net.md_5.bungee.api.ProxyServer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.UUID;
import java.util.stream.Stream;

/**
 * Created by Adrian D. on 01.06.2018.
 */
public class PubSubListener
{
    public PubSubListener()
    {
        Jedis jSubscriber = new Jedis();
        jSubscriber.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                final String uuid = message.split(":")[0];
                final Integer violation = Integer.valueOf(message.split(":")[1]);
                final Stream<String> command = ViolationManager.getInstance()
                        .getCommands(ViolationManager.getInstance().getViolation(UUID.fromString(uuid)), ViolationManager.getInstance().getViolation(UUID.fromString(uuid)) + violation);

                command.forEachOrdered(s ->
                    ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), s)
                );
            }
        }, "intave-violation");
    }


}
