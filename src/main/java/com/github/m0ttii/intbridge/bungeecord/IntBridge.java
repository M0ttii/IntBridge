package com.github.m0ttii.intbridge.bungeecord;

import com.github.m0ttii.intbridge.bungeecord.utils.Configuration;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import redis.clients.jedis.Jedis;
import redis.embedded.RedisServer;

import java.io.File;
import java.io.IOException;


/**
 * Created by Adrian D. on 01.06.2018.
 */
public class IntBridge extends Plugin {
    @Getter Jedis jedis;
    RedisServer redisServer;

    @Getter
    private static IntBridge instance;

    public void onEnable()
    {
        instance = this;
        if(!getDataFolder().exists())
            getDataFolder().mkdir();
        setUpRedis();
    }

    public void onDisable()
    {
        this.redisServer.stop();
    }

    private void setUpRedis()
    {
        if(!Configuration.getConfiguration().getConfig().getBoolean("use-own-redis-server"))
        {
            this.jedis = new Jedis("localhost", Configuration.getConfiguration().getConfig().getInt("redis.port"));
            return;
        }
        this.redisServer = RedisServer.builder()
            .port(Configuration.getConfiguration().getConfig().getInt("redis.port"))
            .setting("masterauth " + Configuration.getConfiguration().getConfig().getString("redis.password"))
            .build();
        this.redisServer.start();
        this.jedis = new Jedis(Configuration.getConfiguration().getConfig().getString("redis.host"), Configuration.getConfiguration().getConfig().getInt("redis.port"));
        this.jedis.auth(Configuration.getConfiguration().getConfig().getString("redis.password"));
    }

    public static IntBridge getInstance()
    {
        return instance;
    }

    public Jedis getJedis()
    {
        return jedis;
    }
}
