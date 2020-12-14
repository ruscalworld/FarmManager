package ru.ruscalworld.farmmanager;

import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Timestamp;
import java.util.Objects;

public class FarmManager extends JavaPlugin {

    public float multiplier;

    @Override
    public void onEnable() {
        multiplier = (float) getConfig().getDouble("multiplier");

        Objects.requireNonNull(getCommand("farmmanager")).setExecutor(new MainCommand(this));
        Objects.requireNonNull(getCommand("farmmanager")).setTabCompleter(new EmptyTabCompleter());

        Objects.requireNonNull(getCommand("spawnrate")).setExecutor(new SpawnrateCommand());
        Objects.requireNonNull(getCommand("spawnrate")).setTabCompleter(new EmptyTabCompleter());

        /*PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new SpawnListener(this), this);*/
    }

    @Override
    public void onDisable() {
        getConfig().set("multiplier", multiplier);
        saveConfig();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Spawnrate spawnrate = Spawnrate.getByTime(timestamp);
        spawnrate.apply();
    }
}
