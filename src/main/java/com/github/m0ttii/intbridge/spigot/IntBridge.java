package com.github.m0ttii.intbridge.spigot;

import com.github.m0ttii.intbridge.spigot.listener.IntaveListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.Jedis;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;


/**
 * Created by Adrian D. on 01.06.2018.
 */
public class IntBridge extends JavaPlugin
{
    @Getter private static IntBridge instance;
    @Getter Jedis jedis;

    public void onEnable()
    {
        instance = this;
        if(!getDataFolder().exists())
            getDataFolder().mkdir();
        saveDefaultConfig();
        connectToRedis();
        initListeners();
    }

    public void onDisable()
    {

    }

    @Override
    public void saveDefaultConfig()
    {
        final String configname = "spigot-config.yml";
        if(!new File(getDataFolder(), configname).exists())
            saveResource(configname, false);
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

    private void initListeners()
    {
        getServer().getPluginManager().registerEvents(new IntaveListener(),this);
    }

    public void transferStringToBungee(final String s)
    {
        // TODO Transfer String s to bungeecord
    }

    @Override
    public FileConfiguration getConfig()
    {
        // TODO This Method would return config.yml, but we need spigot-config.yml -> override getter
        return super.getConfig();
    }
}
