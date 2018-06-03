package com.github.m0ttii.intbridge.bungeecord.manager;

import com.github.m0ttii.intbridge.bungeecord.IntBridge;
<<<<<<< HEAD
import lombok.Getter;
import redis.clients.jedis.Jedis;
=======
>>>>>>> 8edf6bf98c9843b76ea9942b055c990db9c6c4a7

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Adrian D. on 03.06.2018.
 */
<<<<<<< HEAD
public class ViolationManager {
    Jedis jedis;

    public void addViolation(UUID uuid, Integer violation)
=======
public class ViolationManager
{
    public static void addViolation(UUID uuid, Integer violation)
>>>>>>> 8edf6bf98c9843b76ea9942b055c990db9c6c4a7
    {
        IntBridge.getInstance().getJedis().append(uuid.toString(), String.valueOf(violation));
    }

    public static Integer getViolation(UUID uuid)
    {
        if(IntBridge.getInstance().getJedis().exists(uuid.toString()))
            return Integer.parseInt(IntBridge.getInstance().getJedis().get(uuid.toString()));

        return 0;
    }

    public static Stream<String> getCommands(final int oldVL, final int newVL)
    {
        final AtomicReference<Stream<String>> g = new AtomicReference<>(Stream.empty());

        IntStream
                .rangeClosed(oldVL + 1, newVL)
                .boxed()
                .filter(ViolationManager::hasCommand)
                .map(ViolationManager::getCommandsAt)
                .distinct()
                .collect(Collectors.toList())
                .forEach(f -> g.set(Stream.concat(g.get(), f)));

        return g.get();
    }

    private static boolean hasCommand(final int vl)
    {
        return IntBridge.getInstance().getConfig().getSection("pointcommands").contains(String.valueOf(vl));
    }

    private static Stream<String> getCommandsAt(final int vl)
    {
        net.md_5.bungee.config.Configuration g = IntBridge.getInstance().getConfig().getSection("pointcommands");
        if(g.contains(String.valueOf(vl)))
        {
            if(g.getString(String.valueOf(vl),"").length() < 2)
            {
                return g.getStringList(String.valueOf(vl)).stream();
            }
            else
            {
                return Stream.of(g.getString(String.valueOf(vl)));
            }
        }
        else
        {
            return Stream.of("");
        }
    }
}
