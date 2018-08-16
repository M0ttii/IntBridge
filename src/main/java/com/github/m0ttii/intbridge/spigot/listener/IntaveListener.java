package com.github.m0ttii.intbridge.spigot.listener;

import com.github.m0ttii.intbridge.spigot.IntBridge;
import com.github.m0ttii.intbridge.spigot.utils.ConfigHelper;
import de.jpx3.intave.api.external.linked.event.AsyncIntaveCommandTriggerEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Jpx3 on 02.06.2018.
 */

<<<<<<< HEAD
public class IntaveListener implements Listener {
    @EventHandler
=======
public class IntaveListener implements Listener
{
    @EventHandler
<<<<<<< HEAD
    public void on(final AsyncIntaveCommandTriggerEvent event)
=======
>>>>>>> 4943fe1debeabb095890902588397fa4267a627e
    public void on(final AsyncIntaveCommandTriggerEvent e)
>>>>>>> bd49e162d30be782d0c2fc71aae4c6f26fdba991
    {
<<<<<<< HEAD
        final Player player = e.getPlayer();
        String unparsedCommand = e.getCommand();
=======
        final Player p = event.getPlayer();
        String unparsedCommand = event.getCommand();
>>>>>>> 4943fe1debeabb095890902588397fa4267a627e

        if(unparsedCommand.startsWith("/"))
            unparsedCommand = unparsedCommand.replaceFirst("/", "");

        final boolean isKick = unparsedCommand.startsWith("kick");
        final boolean executeCommand = IntBridge.getInstance().getConfig().getBoolean("execute-command");
        final boolean clearVl = IntBridge.getInstance().getConfig().getBoolean("clear-intave-vl");

        if(isKick)
        {
            final String[] args = unparsedCommand.split(" ");
            final String playername = player.getName();
            final UUID playeruuid = player.getUniqueId();
            final StringBuilder reason = new StringBuilder();

            for (int i = 1; args.length > i; i++)
                reason.append(args[i]);

            final String foundReason = ConfigHelper.getKickReasonsFrom(IntBridge.getInstance().getConfig())
                    .filter(s -> reason.toString().endsWith(s)).collect(Collectors.joining());

            if(foundReason.length() > 1)
            {
<<<<<<< HEAD
                final String data = playeruuid.toString() + ":" + ConfigHelper.getPointsForKickReason(IntBridge.getInstance().getConfig(),foundReason);
                IntBridge.getInstance().transferStringToBungee(player, data);
=======
                final String data = String.valueOf(ConfigHelper.getPointsForKickReason(IntBridge.getInstance().getConfig(),foundReason));
                IntBridge.getInstance().transferStringToBungee(Bukkit.getPlayer(playeruuid), data);
>>>>>>> 4943fe1debeabb095890902588397fa4267a627e
            }

            if(clearVl)
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "intave resetvl " + playername);
            if(!executeCommand)
                event.setCancelled(true);
        }
    }
}
