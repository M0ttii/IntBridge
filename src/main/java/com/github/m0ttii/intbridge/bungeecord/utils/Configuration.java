package com.github.m0ttii.intbridge.bungeecord.utils;


import com.github.m0ttii.intbridge.bungeecord.IntBridge;
import lombok.Getter;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import static com.sun.org.apache.xerces.internal.utils.SecuritySupport.getResourceAsStream;

/**
 * Created by Adrian D. on 01.06.2018.
 */
public class Configuration {
    @Getter
    File file;
    @Getter
    net.md_5.bungee.config.Configuration config;
    @Getter
    private static Configuration configuration;


    public Configuration() throws IOException {
        this.file = new File(IntBridge.getInstance().getDataFolder(), "bungeecord-config.yml");
        this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);

        if (!IntBridge.getInstance().getDataFolder().exists())
            IntBridge.getInstance().getDataFolder().mkdir();

        File file = new File(IntBridge.getInstance().getDataFolder(), "config.yml");


        if (!file.exists()) {
            try (InputStream in = getResourceAsStream("bungeecord-config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Configuration getConfiguration()
    {
        return configuration;
    }

    public net.md_5.bungee.config.Configuration getConfig()
    {
        return config;
    }
}
