package com.github.m0ttii.intbridge.bungeecord.utils;


import com.github.m0ttii.intbridge.bungeecord.IntBridge;
import lombok.Getter;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Adrian D. on 01.06.2018.
 */
public class Configuration {
    File file;

    @Getter
    net.md_5.bungee.config.Configuration config;
    @Getter
    private static Configuration configuration;


    public Configuration() throws IOException {
        this.file = new File(IntBridge.getInstance().getDataFolder(), "bungeecord-config.yml");
        this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
    }

}
