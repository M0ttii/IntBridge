package com.github.m0ttii.intbridge.spigot;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.Jedis;


/**
 * Created by Adrian D. on 01.06.2018.
 */
public class IntBridge extends JavaPlugin {
    @Getter private static IntBridge instance;
    @Getter Jedis jedis;


    public void onEnable(){
        saveDefaultConfig();
        connectToRedis();



    }

    public void onDisable(){

    }

    private void connectToRedis(){
        this.jedis = new Jedis(getConfig().getString("redis.host"), getConfig().getInt("redis.port"));
        String authString = jedis.auth(getConfig().getString("redis.password"));
        if (!authString.equals("OK"))
        {
            System.err.println("IntBridge: Redis Auth failed. Wrong password.");
            Bukkit.shutdown();
            return;
        }
        this.jedis.auth(getConfig().getString("redis.password"));
    }

}
