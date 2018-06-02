package com.github.m0ttii.intbridge.spigot;

import com.github.m0ttii.intbridge.spigot.listener.IntaveListener;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.Jedis;

/**
 * Created by Adrian D. on 01.06.2018.
 */
public class IntBridge extends JavaPlugin
{
    @Getter
    private static IntBridge instance;

    public void onEnable()
    {
        instance = this;

        if(!getDataFolder().exists())
            getDataFolder().mkdir();

        saveDefaultConfig();

        initListeners();
    }

    public void onDisable()
    {

    }

    private void initListeners()
    {
        getServer().getPluginManager().registerEvents(new IntaveListener(),this);
    }

    public void transferStringToBungee(final String s)
    {
        // TODO Transfer String s to bungeecord
    }

    public static IntBridge getInstance()
    {
        return instance;
    }

    @Override
    public FileConfiguration getConfig()
    {
        // TODO This Method would return config.yml, but we need spigot-config.yml -> override getter
        return super.getConfig();
    }
}
