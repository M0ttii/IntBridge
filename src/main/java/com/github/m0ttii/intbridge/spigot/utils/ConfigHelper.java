package com.github.m0ttii.intbridge.spigot.utils;

import org.bukkit.configuration.Configuration;

import java.util.stream.Stream;

/**
 * Created by Jpx3 on 02.06.2018.
 */

public class ConfigHelper
{
    public static Stream<String> getKickReasonsFrom(final Configuration configuration)
    {
        return configuration.getConfigurationSection("points").getKeys(false).stream();
    }

    public static int getPointsForKickReason(final Configuration configuration, final String kickreason)
    {
        return configuration.getConfigurationSection("points").getInt(kickreason);
    }
}
