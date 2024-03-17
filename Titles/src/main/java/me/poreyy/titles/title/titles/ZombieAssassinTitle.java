package me.poreyy.titles.title.titles;

import lombok.AllArgsConstructor;
import me.poreyy.titles.title.Title;
import me.poreyy.titles.user.User;
import me.poreyy.titles.user.UserManager;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

@AllArgsConstructor
public class ZombieAssassinTitle extends Title {

    private final UserManager userManager;

    @Override
    public String getName() {
        return "Zombie Assassin";
    }

    @Override
    public String getDescription() {
        return "&7Defeat 30 zombies.";
    }

    @Override
    public Material getIcon() {
        return Material.ZOMBIE_HEAD;
    }

    public void handleDeath(EntityDeathEvent e) {
        if(e.getEntityType() != EntityType.ZOMBIE) return;

        Player killer = e.getEntity().getKiller();

        if(killer == null) return;

        User user = userManager.getUser(killer.getUniqueId());

        int zombieKills = user.getZombieKills();

        user.setZombieKills(zombieKills + 1);

        if(zombieKills + 1 >= 30) {
            user.getOwnedTitles().add(this);
            killer.sendMessage("&aUnlocked title &eZombie Assassin&a!");
        }
    }

    public void handleDamage(EntityDamageByEntityEvent e) {
        if(e.getEntityType() != EntityType.ZOMBIE) return;

        Entity attackerEntity = e.getDamager();

        if(attackerEntity.getType() != EntityType.PLAYER) return;

        Player attacker = (Player) attackerEntity;

        User user = userManager.getUser(attacker.getUniqueId());

        if(user.getActiveTitle() instanceof ZombieAssassinTitle)
            e.setDamage(e.getDamage() * 2);
    }
}