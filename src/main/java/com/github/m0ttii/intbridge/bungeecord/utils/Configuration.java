package com.github.m0ttii.intbridge.bungeecord.utils;


import com.github.m0ttii.intbridge.bungeecord.IntBridge;
import lombok.Getter;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * Created by Adrian D. on 01.06.2018.
 */
public class Configuration {
    @Getter
    File file;
    @Getter
    private static net.md_5.bungee.config.Configuration config;
    @Getter
    private static Configuration configuration;


    public Configuration()
    {
        System.out.println("DEBUG------------------------------");
        try
        {
            this.file = new File(IntBridge.getInstance().getDataFolder(), "bungeecord-config.yml");


            if (!file.exists())
            {
                InputStream in = IntBridge.getInstance().getResourceAsStream("bungeecord-config.yml");
                Files.copy(in, file.toPath());
                in.close();
            }
            this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
