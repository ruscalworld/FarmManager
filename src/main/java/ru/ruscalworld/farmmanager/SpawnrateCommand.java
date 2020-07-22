package ru.ruscalworld.farmmanager;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SpawnrateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, Command command, @NotNull String alias, @NotNull String[] args) {

        if (command.getLabel().equals("spawnrate")) {
            int monsters = Bukkit.getMonsterSpawnLimit();
            int animals = Bukkit.getAnimalSpawnLimit();
            int water_animals = Bukkit.getWaterAnimalSpawnLimit();
            int ambient = Bukkit.getAmbientSpawnLimit();
            int water_ambient = Bukkit.getServer().getWaterAmbientSpawnLimit();

            commandSender.sendMessage("§f§lТекущие параметры спавна мобов: \n" +
                    "§7§l * §fМонстры: §9" + monsters + "\n" +
                    "§7§l * §fЖивотные: §9" + animals + "\n" +
                    "§7§l * §fВодные животные: §9" + water_animals + "\n" +
                    "§7§l * §fAmbient: §9" + ambient + "\n" +
                    "§7§l * §fWater Ambient: §9" + water_ambient);
        }

        return false;
    }
}
