package me.poreyy.titles.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.poreyy.titles.title.Title;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Setter
@Getter
public class User {

    private final UUID uuid;

    private int zombieKills;

    private int wood_broken;

    private final List<Title> ownedTitles;

    private Title activeTitle;
}