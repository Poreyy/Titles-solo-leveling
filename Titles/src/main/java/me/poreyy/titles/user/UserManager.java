package me.poreyy.titles.user;

import me.poreyy.titles.user.data.DataHandler;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserManager {

    private final DataHandler dataHandler;

    public UserManager(DataHandler dataHandler) {
        this.dataHandler = dataHandler;

        Bukkit.getOnlinePlayers().forEach(player -> loadUserData(player.getUniqueId()));
    }

    private final Map<UUID, User> users = new HashMap<>();

    public void loadUserData(UUID uuid) {
        this.users.put(uuid, dataHandler.loadUserData(uuid));
    }

    public void saveUserData(UUID uuid) {
        User user = users.remove(uuid);

        if(user == null) return;

        dataHandler.saveUserData(uuid, user);
    }

    public User getUser(UUID uuid) {
        return users.get(uuid);
    }
}