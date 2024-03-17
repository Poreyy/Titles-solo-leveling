package me.poreyy.titles.title;

import lombok.AllArgsConstructor;
import me.poreyy.titles.title.titles.ZombieAssassinTitle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

@AllArgsConstructor
public class TitleListener implements Listener {

    private final ZombieAssassinTitle zombieAssassinTitle;

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        zombieAssassinTitle.handleDeath(e);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        zombieAssassinTitle.handleDamage(e);
    }
}