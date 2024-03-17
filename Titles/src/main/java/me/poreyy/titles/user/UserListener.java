package me.poreyy.titles.user;

import lombok.AllArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
public class UserListener implements Listener {

    private final UserManager userManager;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(AsyncPlayerPreLoginEvent e) {
        if(e.getLoginResult() != AsyncPlayerPreLoginEvent.Result.ALLOWED) return;

        userManager.loadUserData(e.getUniqueId());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        CompletableFuture.runAsync(() -> userManager.saveUserData(e.getPlayer().getUniqueId()));
    }
}