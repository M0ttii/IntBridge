package com.github.m0ttii.intbridge.bungeecord.manager;

import com.github.m0ttii.intbridge.bungeecord.IntBridge;
import com.github.m0ttii.intbridge.bungeecord.utils.Configuration;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Adrian D. on 03.06.2018.
 */
public class ViolationManager
{
    Jedis jedis;

    public void addViolation(UUID uuid, Integer violation)
    {
        this.jedis = IntBridge.getInstance().getJedis();

        jedis.append(uuid.toString(), String.valueOf(violation));
    }

    public Integer getViolation(UUID uuid)
    {
        this.jedis = IntBridge.getInstance().getJedis();
        if(jedis.exists(uuid.toString()))
            return Integer.parseInt(jedis.get(uuid.toString()));

        return 0;
    }

    public Stream<String> getCommands(final int oldVL, final int newVL)
    {
        return IntStream.range(oldVL,newVL + 1).boxed()
                .filter(this::hasCommand).map(this::getCommandsAt).findFirst().get();
    }

    private boolean hasCommand(final int vl)
    {
        return IntBridge.getInstance().getConfig().getSection("pointcommands").contains(vl + "");
    }

    private Stream<String> getCommandsAt(final int vl)
    {
        net.md_5.bungee.config.Configuration g = IntBridge.getInstance().getConfig().getSection("pointcommands");
        if(g.contains(vl + ""))
        {
            if(g.getString(vl + "","").length() < 2)
            {
                return g.getStringList(vl + "").stream();
            }
            else
            {
                return Stream.of(g.getString(vl + ""));
            }
        }
        else
        {
            return Stream.of("");
        }
    }
}
