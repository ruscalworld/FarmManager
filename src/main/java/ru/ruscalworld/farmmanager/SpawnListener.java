package ru.ruscalworld.farmmanager;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class SpawnListener implements Listener {

    private FarmManager plugin;

    public SpawnListener(FarmManager plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSpawn(CreatureSpawnEvent e) {
        LivingEntity entity = e.getEntity();
        float multiplier = plugin.multiplier;

        if (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            if (Math.random() >= multiplier) {
                e.setCancelled(true);
            }
        }
    }
}
