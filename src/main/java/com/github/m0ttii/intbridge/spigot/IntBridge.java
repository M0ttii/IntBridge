package com.github.m0ttii.intbridge.spigot;

import com.github.m0ttii.intbridge.spigot.listener.IntaveListener;
import lombok.Getter;
<<<<<<< HEAD
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.Jedis;

=======
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.Jedis;
>>>>>>> f40385271311cf89216c4782e58011cf4b7062da

/**
 * Created by Adrian D. on 01.06.2018.
 */
<<<<<<< HEAD
public class IntBridge extends JavaPlugin {
    @Getter private static IntBridge instance;
    @Getter Jedis jedis;
=======
public class IntBridge extends JavaPlugin
{
    @Getter
    private static IntBridge instance;
>>>>>>> f40385271311cf89216c4782e58011cf4b7062da

    public void onEnable()
    {
        instance = this;

        if(!getDataFolder().exists())
            getDataFolder().mkdir();

        saveDefaultConfig();
<<<<<<< HEAD
        connectToRedis();



=======

        initListeners();
>>>>>>> f40385271311cf89216c4782e58011cf4b7062da
    }

    public void onDisable()
    {

    }

<<<<<<< HEAD
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

=======
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
>>>>>>> f40385271311cf89216c4782e58011cf4b7062da
}
