package ru.ruscalworld.farmmanager;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class SpawnrateTest {
    @Test
    void spawnrateTest() {
        // Ночь понедельника
        assertEquals(70, Spawnrate.getByTime(new Timestamp(1607893200000L)).getMonsterLimit());
        // День понедельника
        assertEquals(35, Spawnrate.getByTime(new Timestamp(1607929200000L)).getMonsterLimit());
        // Вечер понедельника
        assertEquals(10, Spawnrate.getByTime(new Timestamp(1607950800000L)).getMonsterLimit());

        // Ночь пятницы
        assertEquals(70, Spawnrate.getByTime(new Timestamp(1608238800000L)).getMonsterLimit());
        // День пятницы
        assertEquals(10, Spawnrate.getByTime(new Timestamp(1608274800000L)).getMonsterLimit());
        // Вечер пятницы
        assertEquals(10, Spawnrate.getByTime(new Timestamp(1608296400000L)).getMonsterLimit());
    }
}