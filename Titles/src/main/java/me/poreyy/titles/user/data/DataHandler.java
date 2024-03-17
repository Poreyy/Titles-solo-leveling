package me.poreyy.titles.user.data;

import me.poreyy.titles.user.User;

import java.util.UUID;

public interface DataHandler {

    void saveUserData(UUID uuid, User user);

    User loadUserData(UUID uuid);
}