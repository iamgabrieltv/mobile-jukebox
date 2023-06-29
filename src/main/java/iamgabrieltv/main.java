package iamgabrieltv;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class main extends JavaPlugin {
    @Override
    public void onEnable() {
        File dataFolder = getDataFolder();

        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        File playersFile = new File(dataFolder, "players.json");
        if (!playersFile.exists()) {
            try (InputStream inputStream = getResource("template/players.json")) {
                Files.copy(inputStream, playersFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Register the /jukebox command
        getCommand("jukebox").setExecutor(new JukeboxCommandExecutor(playersFile));

        // Register the EventListener
        Bukkit.getPluginManager().registerEvents(new JukeboxListener(playersFile), this);

        getLogger().info("Success!");

    }
}