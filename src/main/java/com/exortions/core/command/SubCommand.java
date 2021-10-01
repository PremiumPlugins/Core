package com.exortions.core.command;

import com.exortions.core.CorePlugin;
import com.exortions.core.annotation.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface SubCommand<T extends CorePlugin> {

    default String name() {
        if (getClass().isAnnotationPresent(Name.class)) return getClass().getDeclaredAnnotation(Name.class).name();
        return getClass().getName().split("\\.")[getClass().getName().split("\\.").length-1].toLowerCase().replaceAll("command", "");
    }

    default String permission() {
        if (getClass().isAnnotationPresent(Permission.class)) return getClass().getDeclaredAnnotation(Permission.class).permission();
        return getPlugin().getPluginName().toLowerCase() + "." + name();
    }

    default String usage() {
        if (getClass().isAnnotationPresent(Usage.class)) return getClass().getDeclaredAnnotation(Usage.class).usage();
        return "/" + getPlugin().getPluginName().toLowerCase() + " " + name();
    }

    default String description() {
        if (getClass().isAnnotationPresent(Description.class)) return getClass().getDeclaredAnnotation(Description.class).description();
        return "";
    }

    default boolean requiresPlayer() {
        if (getClass().isAnnotationPresent(RequiresPlayer.class)) return getClass().getDeclaredAnnotation(RequiresPlayer.class).requiresPlayer();
        return false;
    }

    default void execute(Player player, String[] args) {}
    default void execute(CommandSender sender, String[] args) {}

    T getPlugin();

}
