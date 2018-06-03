package com.github.m0ttii.intbridge.bungeecord.manager;

import com.github.m0ttii.intbridge.bungeecord.IntBridge;
import com.github.m0ttii.intbridge.bungeecord.utils.Configuration;
import redis.clients.jedis.Jedis;

import java.util.UUID;

/**
 * Created by Adrian D. on 03.06.2018.
 */
public class ViolationManager
{
    Jedis jedis;

    public void addViolation(UUID uuid, Integer violation)
    {
        this.jedis = IntBridge.getInstance().getJedis();

        jedis.append(uuid.toString(), String.valueOf(violation));
    }

    public Integer getViolation(UUID uuid)
    {
        this.jedis = IntBridge.getInstance().getJedis();
        if(jedis.exists(uuid.toString()))
            return Integer.parseInt(jedis.get(uuid.toString()));

        return 0;
    }
}
