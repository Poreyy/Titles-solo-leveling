package me.poreyy.titles.user.data;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import me.poreyy.titles.TitlesPlugin;
import me.poreyy.titles.title.Title;
import me.poreyy.titles.user.User;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class YamlDataHandler implements DataHandler {

    private final TitlesPlugin plugin;

    @SneakyThrows
    @Override
    public void saveUserData(UUID uuid, User user) {
        File playerFile = getUserDataFile(uuid);
        YamlConfiguration config = YamlConfiguration.loadConfiguration(playerFile);

        config.set("zombieKills", user.getZombieKills());
        config.set("woodBroken", user.getWood_broken());
        config.set("ownedTitles", user.getOwnedTitles());
        config.set("activeTitle", user.getActiveTitle());

        config.save(playerFile);
    }

    @Override
    public User loadUserData(UUID uuid) {
        File playerFile = getUserDataFile(uuid);
        if (!playerFile.exists()) return new User(uuid, 0, 0, new ArrayList<>(), null);

        YamlConfiguration config = YamlConfiguration.loadConfiguration(playerFile);

        int zombieKills = config.getInt("zombieKills");
        int woodBroken = config.getInt("woodBroken");
        List<Title> ownedTitles = (List<Title>) config.getList("ownedTitles");
        Title activeTitle = (Title) config.get("activeTitle");

        return new User(uuid, zombieKills, woodBroken, ownedTitles, activeTitle);
    }

    @SneakyThrows
    public File getUserDataFile(UUID uuid) {
        File playerDataFolder = new File(plugin.getDataFolder(), "playerdata");
        File playerFile = new File(playerDataFolder, uuid.toString() + ".yml");

        if (!playerDataFolder.exists())
            if (!playerDataFolder.mkdirs())
                throw new IOException("Could not create folder");
        return playerFile;
    }
}