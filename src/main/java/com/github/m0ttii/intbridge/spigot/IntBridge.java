package com.github.m0ttii.intbridge.spigot;

import com.github.m0ttii.intbridge.spigot.listener.IntaveListener;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;


/**
 * Created by Adrian D. on 01.06.2018.
 */
public class IntBridge extends JavaPlugin{
    @Getter private static IntBridge instance;
    File file;
    private FileConfiguration spigotconfig;

    public void onEnable()
    {
        instance = this;
        if(!getDataFolder().exists())
            getDataFolder().mkdir();
        saveDefaultConfig();
        initListeners();
    }

    public void onDisable() {

    }

    @Override
    public void saveDefaultConfig()
    {
        final String configname = "spigot-config.yml";
        this.file = new File(getDataFolder(), configname);
        if(!this.file.exists())
            saveResource(configname, false);
        this.spigotconfig = YamlConfiguration.loadConfiguration(this.file);
    }


    private void initListeners()
    {
        getServer().getPluginManager().registerEvents(new IntaveListener(),this);
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    public void transferStringToBungee(final Player player, final String string)
    {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(string);
        player.sendPluginMessage(this, "BungeeCord", out.toByteArray());
    }


    @Override
    public FileConfiguration getConfig() {
        return this.spigotconfig;
    }
}