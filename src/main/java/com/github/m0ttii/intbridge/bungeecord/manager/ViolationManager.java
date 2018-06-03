package com.github.m0ttii.intbridge.bungeecord.manager;

import com.github.m0ttii.intbridge.bungeecord.IntBridge;
import com.github.m0ttii.intbridge.bungeecord.utils.Configuration;
import redis.clients.jedis.Jedis;

import java.util.UUID;

/**
 * Created by Adrian D. on 03.06.2018.
 */
public class ViolationManager {
    Jedis jedis;



    private void addViolation(UUID uuid, Integer violation){
        this.jedis = new IntBridge().getJedis();

        jedis.append(uuid.toString(), String.valueOf(violation));
    }

    private String getViolation(UUID uuid){
        this.jedis = new IntBridge().getJedis();

        if(jedis.exists(uuid.toString())){
            return jedis.get(uuid.toString());
        }
        return null;

    }

    private String getCommand(UUID uuid){
        if(getViolation(uuid) == null){
            return null;
        }
        Integer violation = Integer.valueOf(getViolation(uuid));
        Configuration.getConfiguration().getConfig().getStringList("commands").forEach(s -> {
            Integer neededViolation = Integer.valueOf(s.split(";")[0]);
            String string = Configuration.getConfiguration().getConfig().getStringList("commands").iterator().next();
            if()
        });

    }
}
