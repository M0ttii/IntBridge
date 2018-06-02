package com.github.m0ttii.intbridge.spigot;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Adrian D. on 01.06.2018.
 */
public class IntBridge extends JavaPlugin {
    @Getter private static IntBridge instance;


    public void onEnable(){
        saveDefaultConfig();
    }

    public void onDisable(){

    }

}
