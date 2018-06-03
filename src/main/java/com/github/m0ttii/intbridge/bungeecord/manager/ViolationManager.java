package com.github.m0ttii.intbridge.bungeecord.manager;

import com.github.m0ttii.intbridge.bungeecord.IntBridge;
<<<<<<< HEAD
import com.github.m0ttii.intbridge.bungeecord.utils.Configuration;
import lombok.Getter;
=======
>>>>>>> dbad2d7a419a695436bcdb1bca7055c552142bf3
import redis.clients.jedis.Jedis;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Adrian D. on 03.06.2018.
 */
public class ViolationManager {
    @Getter private static ViolationManager instance;
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
        final AtomicReference<Stream<String>> g = new AtomicReference<>(Stream.empty());

        IntStream
                .rangeClosed(oldVL + 1, newVL)
                .boxed()
                .filter(this::hasCommand)
                .map(this::getCommandsAt)
                .distinct()
                .collect(Collectors.toList())
                .forEach(f -> g.set(Stream.concat(g.get(), f)));

        return g.get();
    }

    private boolean hasCommand(final int vl)
    {
        return IntBridge.getInstance().getConfig().getSection("pointcommands").contains(String.valueOf(vl));
    }

    private Stream<String> getCommandsAt(final int vl)
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
