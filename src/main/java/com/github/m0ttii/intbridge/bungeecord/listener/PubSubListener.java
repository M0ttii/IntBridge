package com.github.m0ttii.intbridge.bungeecord.listener;

import com.github.m0ttii.intbridge.bungeecord.IntBridge;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by Adrian D. on 01.06.2018.
 */
public class PubSubListener{

<<<<<<< HEAD
    public PubSubListener(){
        Jedis jSubscriber = new Jedis();
        jSubscriber.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                // violation actions
            }
        }, "intave-violation");
=======
    public void onMessage(String channel, String message)
    {
        if(channel.equalsIgnoreCase("intave-point"))
        {
            final String[] responce = message.split(":");

            final String uuid = responce[0];
            final String pointstoadd = responce[1];



        }
>>>>>>> f40385271311cf89216c4782e58011cf4b7062da
    }


}
