package com.exortions.core.command;

import com.exortions.core.util.ArrayUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class SubCommandExecutor implements CommandExecutor {

    private List<SubCommand<?>> commands;

    private Consumer<CommandSender> onlyPlayers;
    private Consumer<CommandSender> noPermission;
    private Consumer<CommandSender> noArguments;
    private Consumer<CommandSender> commandNotFound;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            noArguments.accept(sender);
            return true;
        }

        for (SubCommand<?> cmd : commands) {
            if (!args[0].equals(cmd.name())) return true;
            if (!sender.hasPermission(cmd.permission())) { noPermission.accept(sender); return true; }
            if (args.length > 1) args = ArrayUtils.subArray(args); else args = new String[0];
            if (cmd.requiresPlayer()) if (sender instanceof Player) cmd.execute((Player) sender, args); else onlyPlayers.accept(sender);
            else cmd.execute(sender, args);
            return false;
        }

        commandNotFound.accept(sender);
        return false;
    }

}
