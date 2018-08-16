package com.github.m0ttii.intbridge.bungeecord;

import com.github.m0ttii.intbridge.bungeecord.listener.PluginMessageListener;
import com.github.m0ttii.intbridge.bungeecord.utils.Configuration;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;


/**
 * Created by Adrian D. on 01.06.2018.
 */
public class IntBridge extends Plugin {


    @Getter
    private static IntBridge instance;

    public void onEnable()
    {
        instance = this;
        if(!getDataFolder().exists())
            getDataFolder().mkdir();
        new Configuration();
        new PluginMessageListener();
    }

    public void onDisable()
    {

    }




    public net.md_5.bungee.config.Configuration getConfig()
    {
        return Configuration.getConfiguration().getConfig();
    }
}
