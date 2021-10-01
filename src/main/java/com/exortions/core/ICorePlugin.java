package com.exortions.core;

import com.exortions.core.update.UpdateChecker;
import org.bukkit.Bukkit;

public interface ICorePlugin {

    default void registerListeners() {}
    default void registerCommands() {}

    default boolean checkForUpdates() {
        new UpdateChecker().getLatestUpdate(this, version -> {
            if (version.equals(getPluginVersion())) {
                Bukkit.getConsoleSender().sendMessage(getPrefix() + "Plugin is up-to date.");
            } else {
                Bukkit.getConsoleSender().sendMessage(getPrefix() + "Plugin is out of date!\nCurrent version: " + getPluginVersion() + ", Latest version:  " + version);
            }
        });

        return true;
    }
    default boolean loadConfiguration() { return true; }
    default boolean loadLanguages() { return true; }
    default boolean loadMessages() { return true; }
    default boolean loadStorage() { return true; }
    default boolean loadData() { return true; }
    default boolean loadMetrics() { return true; }

    CorePlugin getCorePlugin();

    default void sendStartupMessage() {}
    default int getPluginId() { return 0; }

    String getPluginName();
    String getPluginVersion();

    String getPrefix();

}
