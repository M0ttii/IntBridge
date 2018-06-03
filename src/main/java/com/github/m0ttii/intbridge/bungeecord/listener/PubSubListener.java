package com.github.m0ttii.intbridge.bungeecord.listener;

import com.github.m0ttii.intbridge.bungeecord.IntBridge;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by Adrian D. on 01.06.2018.
 */
public class PubSubListener{

    public PubSubListener(){
        Jedis jSubscriber = new Jedis();
        jSubscriber.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                // violation actions
            }
        }, "intave-violation");
    }


}
