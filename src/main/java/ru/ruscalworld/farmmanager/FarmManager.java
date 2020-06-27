package ru.ruscalworld.farmmanager;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
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

        Calendar cal = Calendar.getInstance();

        int monster_limit;
        int animal_limit;
        int water_animal_limit;
        int ambient_limit;
        int water_ambient_limit;

        cal.setTimeInMillis(System.currentTimeMillis());
        int day = cal.get(Calendar.DAY_OF_WEEK);
        int hour = cal.get(Calendar.HOUR_OF_DAY);

        monster_limit = 7;
        animal_limit = 2;
        water_animal_limit = 1;
        ambient_limit = 1;
        water_ambient_limit = 1;

        if (hour == 0) {
            monster_limit = 70;
            animal_limit = 15;
            water_animal_limit = 5;
            ambient_limit = 15;
            water_ambient_limit = 20;
        }

        File configFile = new File(getDataFolder() + "/../..", "bukkit.yml");
        YamlConfiguration config = new YamlConfiguration();

        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        config.set("spawn-limits.monsters", monster_limit);
        config.set("spawn-limits.animals", animal_limit);
        config.set("spawn-limits.water-animals", water_animal_limit);
        config.set("spawn-limits.ambient", ambient_limit);
        config.set("spawn-limits.water-ambient", water_ambient_limit);

        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
