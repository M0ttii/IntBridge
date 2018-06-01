package com.github.m0ttii.intbridge.bungeecord.listener;

import com.github.m0ttii.intbridge.bungeecord.IntBridge;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by Adrian D. on 01.06.2018.
 */
public class PubSubListener extends JedisPubSub {

    public void onMessage(String channel, String message){
        if(channel.equalsIgnoreCase("intave-point")){
            String uuid = message.split(":")[0];
            String pointstoadd = message.split(":")[0];
            IntBridge.getInstance().getJedis().

        }
    }
}
