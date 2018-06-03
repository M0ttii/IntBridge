package com.github.m0ttii.intbridge.bungeecord;

import com.github.m0ttii.intbridge.bungeecord.utils.Configuration;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;
import redis.clients.jedis.Jedis;
import redis.embedded.RedisServer;

import java.io.IOException;


/**
 * Created by Adrian D. on 01.06.2018.
 */
public class IntBridge extends Plugin {
    @Getter
    Jedis jedis;
    RedisServer redisServer;

    @Getter
    private static IntBridge instance;

    public void onEnable() {
        instance = this;
        if (!getDataFolder().exists())
            getDataFolder().mkdir();
        setUpRedis();
    }

    public void onDisable() {
        this.redisServer.stop();
    }


    private void init() throws IOException {
        setUpRedis();
        new Configuration();
    }


    private void setUpRedis() {
        if (!Configuration.getConfiguration().getConfig().getBoolean("use-own-redis-server")) {
            this.jedis = new Jedis("localhost", Configuration.getConfiguration().getConfig().getInt("redis.port"));
            String authString = jedis.auth(Configuration.getConfiguration().getConfig().getString("redis.password"));
            if (!authString.equals("OK")) {
                System.err.println("IntBridge: Redis Auth failed. Wrong password.");
                return;
            }
            this.jedis.auth(Configuration.getConfiguration().getConfig().getString("redis.password"));
        }
        this.jedis = new Jedis(Configuration.getConfiguration().getConfig().getString("redis.host"), Configuration.getConfiguration().getConfig().getInt("redis.port"));
        String authString = jedis.auth(Configuration.getConfiguration().getConfig().getString("redis.password"));
        if (!authString.equals("OK")) {
            System.err.println("IntBridge: Redis Auth failed. Wrong password.");
            return;
        }
        this.jedis.auth(Configuration.getConfiguration().getConfig().getString("redis.password"));
    }
}