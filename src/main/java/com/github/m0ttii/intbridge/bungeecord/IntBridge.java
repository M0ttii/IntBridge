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

<<<<<<< HEAD
    private void init() throws IOException {
        setUpRedis();
        new Configuration();
    }


    private void setUpRedis() throws IOException {
        if(Configuration.getConfiguration().getConfig().getBoolean("use-own-redis-server") == false){
            //Start Redis
            this.redisServer = RedisServer.builder()
                    .port(Configuration.getConfiguration().getConfig().getInt("redis.port"))
                    .setting("masterauth " + Configuration.getConfiguration().getConfig().getString("redis.password"))
                    .build();
            this.redisServer.start();
            //Connect to Redis
=======
    private void setUpRedis()
    {
        if(!Configuration.getConfiguration().getConfig().getBoolean("use-own-redis-server"))
        {
>>>>>>> f40385271311cf89216c4782e58011cf4b7062da
            this.jedis = new Jedis("localhost", Configuration.getConfiguration().getConfig().getInt("redis.port"));
            String authString = jedis.auth(Configuration.getConfiguration().getConfig().getString("redis.password"));
            if (!authString.equals("OK"))
            {
                System.err.println("IntBridge: Redis Auth failed. Wrong password.");
                return;
            }
            this.jedis.auth(Configuration.getConfiguration().getConfig().getString("redis.password"));
        }
        this.jedis = new Jedis(Configuration.getConfiguration().getConfig().getString("redis.host"), Configuration.getConfiguration().getConfig().getInt("redis.port"));
        String authString = jedis.auth(Configuration.getConfiguration().getConfig().getString("redis.password"));
        if (!authString.equals("OK"))
        {
            System.err.println("IntBridge: Redis Auth failed. Wrong password.");
            return;
        }
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
