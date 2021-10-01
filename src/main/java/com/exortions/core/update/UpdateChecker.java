package com.exortions.core.update;

import com.exortions.core.ICorePlugin;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

@AllArgsConstructor
public class UpdateChecker {

    public void getLatestUpdate(ICorePlugin plugin, Consumer<String> consumer, @Nullable Runnable... err) {
        try {
            InputStream stream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + plugin.getPluginId()).openStream();
            Scanner scanner = new Scanner(stream);
            if (scanner.hasNext()) consumer.accept(scanner.next());
        } catch (IOException e) {
            if (err == null || err.length == 0) e.printStackTrace();
            else err[0].run();
        }
    }

}
