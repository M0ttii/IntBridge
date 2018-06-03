package com.github.m0ttii.intbridge.spigot.listener;

import com.github.m0ttii.intbridge.bungeecord.IntBridge;
import de.jpx3.intave.api.external.linked.event.AsyncIntaveViolationEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

/**
 * Created by Adrian D. on 03.06.2018.
 */
public class IntaveViolationListener implements Listener {

    @EventHandler
    public void onViolation(AsyncIntaveViolationEvent event){
        UUID player = event.getDetectedPlayer().getUniqueId();
        String check = event.getCheckName();
        Integer violation = event.getAddedVL();
        //Will submit a message in this format: player:check:violation
        String message = player + ":" + check + ":" + violation;

        IntBridge.getInstance().getJedis().publish("intave-violation", message);
    }


}
