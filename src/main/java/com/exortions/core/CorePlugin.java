package com.exortions.core;

import com.exortions.core.command.SubCommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public abstract class CorePlugin extends JavaPlugin implements ICorePlugin {

    public void reload() {
        onDisable();
        onEnable();
    }

    public void registerListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    public void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) Bukkit.getPluginManager().registerEvents(listener, this);
    }

    public void registerMainCommand(String cmd, SubCommandExecutor executor, TabCompleter completer) {
        PluginCommand command = Objects.requireNonNull(getCommand(cmd));
        command.setExecutor(executor);
        command.setTabCompleter(completer);
    }

    public void registerCommand(String cmd, CommandExecutor executor, TabCompleter... completer) {
        PluginCommand command = Objects.requireNonNull(getCommand(cmd));
        command.setExecutor(executor);
        for (TabCompleter tabCompleter : completer) command.setTabCompleter(tabCompleter);
    }

    @Override
    public String getPluginName() {
        return getDescription().getName();
    }

    @Override
    public String getPluginVersion() {
        return getDescription().getVersion();
    }

}
