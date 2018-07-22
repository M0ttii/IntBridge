package com.github.m0ttii.intbridge.bungeecord.manager;

import com.github.m0ttii.intbridge.bungeecord.IntBridge;
import net.md_5.bungee.config.Configuration;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Adrian D. on 03.06.2018.
 */
public class ViolationManager {
    public static HashMap<UUID, Integer> violationmap = new HashMap<UUID, Integer>();

    public static void addViolation(UUID uuid, Integer violation)
    {
        violationmap.put(uuid, violation);
    }

    public static int getViolation(UUID uuid) {
        if (violationmap.containsKey(uuid)) {
            return Integer.parseInt(String.valueOf(violationmap.get(uuid)));

        }
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
        Configuration g = IntBridge.getInstance().getConfig().getSection("pointcommands");
        if(g.contains(String.valueOf(vl)))
            if (g.getString(String.valueOf(vl), "").length() < 2)
                return g.getStringList(String.valueOf(vl)).stream();
            else
                return Stream.of(g.getString(String.valueOf(vl)));
        else
            return Stream.of("");
    }
}
