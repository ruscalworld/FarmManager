package ru.ruscalworld.farmmanager;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

public class Spawnrate {
    private final int monsterLimit;
    private final int animalLimit;
    private final int waterAnimalLimit;
    private final int ambientLimit;
    private final int waterAmbientLimit;

    public Spawnrate(int monsterLimit, int animalLimit, int waterAnimalLimit, int ambientLimit, int waterAmbientLimit) {
        this.monsterLimit = monsterLimit;
        this.animalLimit = animalLimit;
        this.waterAnimalLimit = waterAnimalLimit;
        this.ambientLimit = ambientLimit;
        this.waterAmbientLimit = waterAmbientLimit;
    }

    public Spawnrate() {
        this.monsterLimit = Bukkit.getMonsterSpawnLimit();
        this.animalLimit = Bukkit.getAnimalSpawnLimit();
        this.waterAnimalLimit = Bukkit.getWaterAnimalSpawnLimit();
        this.ambientLimit = Bukkit.getAmbientSpawnLimit();
        this.waterAmbientLimit = Bukkit.getWaterAmbientSpawnLimit();
    }

    public static Spawnrate getByTime(Timestamp time) {
        Calendar cal = Calendar.getInstance();

        int monsterLimit;
        int animalLimit;
        int waterAnimalLimit;
        int ambientLimit;
        int waterAmbientLimit;

        cal.setTimeInMillis(time.getTime());
        int day = cal.get(Calendar.DAY_OF_WEEK);
        int hour = cal.get(Calendar.HOUR_OF_DAY);

        // Лимиты по умолчанию (во всё остальное время)
        monsterLimit = 10;
        animalLimit = 10;
        waterAnimalLimit = 3;
        ambientLimit = 5;
        waterAmbientLimit = 5;

        // Только в 10 часов с понедельника по четверг
        if (hour == 10 && (day == Calendar.MONDAY || day == Calendar.TUESDAY || day == Calendar.WEDNESDAY || day == Calendar.THURSDAY)) {
            monsterLimit = 35;
            animalLimit = 35;
            waterAnimalLimit = 5;
            ambientLimit = 7;
            waterAmbientLimit = 8;
        }

        // Ночью всегда стандартные значения
        if (hour == 0) {
            monsterLimit = 70;
            animalLimit = 15;
            waterAnimalLimit = 5;
            ambientLimit = 15;
            waterAmbientLimit = 20;
        }

        return new Spawnrate(monsterLimit, animalLimit, waterAnimalLimit, ambientLimit, waterAmbientLimit);
    }

    public void apply() {
        String path = Bukkit.getWorldContainer().getAbsolutePath();
        File configFile = new File(path, "bukkit.yml");
        YamlConfiguration config = new YamlConfiguration();

        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        config.set("spawn-limits.monsters", this.monsterLimit);
        config.set("spawn-limits.animals", this.animalLimit);
        config.set("spawn-limits.water-animals", this.waterAnimalLimit);
        config.set("spawn-limits.ambient", this.ambientLimit);
        config.set("spawn-limits.water-ambient", this.waterAmbientLimit);

        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getMonsterLimit() {
        return this.monsterLimit;
    }

    public int getAnimalLimit() {
        return this.animalLimit;
    }

    public int getWaterAnimalLimit() {
        return this.waterAnimalLimit;
    }

    public int getAmbientLimit() {
        return this.ambientLimit;
    }

    public int getWaterAmbientLimit() {
        return this.waterAmbientLimit;
    }
}
