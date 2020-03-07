package ru.ruscalworld.farmmanager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class MainCommand implements CommandExecutor {

    private FarmManager plugin;

    public MainCommand(FarmManager plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, Command command, @NotNull String alias, @NotNull String[] args) {

        if (command.getLabel().equals("farmmanager")) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reload")) {
                    plugin.reloadConfig();
                }

                if (args[0].equalsIgnoreCase("setmultiplier")) {
                    if (args.length == 2) {
                        float multiplier = Float.parseFloat(args[1]);
                        plugin.multiplier = multiplier;
                        commandSender.sendMessage("§fSet spawn multiplier to §9" + multiplier + "§f!");
                    }
                }
            } else commandSender.sendMessage("§8§l=====================\n" +
                    " §9FarmManager §fis a simple plugin to schedule change of mob spawn limits.\n" +
                    " §fWritten by §9RuscalWorld §ffor §6Bortexel §fMinecraft server.\n" +
                    " §fIP: §9play.bortexelmc.ru\n" +
                    " §fSource code: §9https://github.com/RuscalWorld/FarmManager\n" +
                    " §fPlugin version: §9" + plugin.getDescription().getVersion() + "\n" +
                    " §f© RuscalWorld, 2020\n" +
                    "§8§l=====================");
        }

        return false;
    }
}
