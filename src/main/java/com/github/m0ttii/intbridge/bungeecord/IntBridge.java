package com.github.m0ttii.intbridge.bungeecord;

import com.github.m0ttii.intbridge.bungeecord.listener.PubSubListener;
import com.github.m0ttii.intbridge.bungeecord.utils.Configuration;
import lombok.Getter;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import redis.clients.jedis.Jedis;
import redis.embedded.RedisServer;


/**
 * Created by Adrian D. on 01.06.2018.
 */
public class IntBridge extends Plugin
{
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
        new PubSubListener();
    }

    public void onDisable()
    {
        this.redisServer.stop();
        this.jedis.quit();
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

    public net.md_5.bungee.config.Configuration getConfig()
    {
        return Configuration.getConfiguration().getConfig();
    }
}
