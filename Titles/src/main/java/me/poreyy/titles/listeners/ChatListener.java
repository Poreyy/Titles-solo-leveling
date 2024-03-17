package me.poreyy.titles.listeners;

import lombok.AllArgsConstructor;
import me.poreyy.titles.title.Title;
import me.poreyy.titles.user.User;
import me.poreyy.titles.user.UserManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@AllArgsConstructor
public class ChatListener implements Listener {

    private final UserManager userManager;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        User user = userManager.getUser(e.getPlayer().getUniqueId());

        Title activeTitle = user.getActiveTitle();

        String title = activeTitle == null ? "No Title" : activeTitle.toString();

        e.setFormat("[" + title + "] <%s> %s");
    }
}