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

            } /* TODO Default command response*/
        }

        return false;
    }
}
