package me.poreyy.titles.commands;

import com.samjakob.spigui.SpiGUI;
import com.samjakob.spigui.buttons.SGButton;
import com.samjakob.spigui.item.ItemBuilder;
import com.samjakob.spigui.menu.SGMenu;
import lombok.AllArgsConstructor;
import me.poreyy.titles.title.Title;
import me.poreyy.titles.title.titles.ZombieAssassinTitle;
import me.poreyy.titles.user.User;
import me.poreyy.titles.user.UserManager;
import me.poreyy.titles.utils.Colorize;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

@AllArgsConstructor
public class TitleCommand implements CommandExecutor {

    private final SpiGUI spiGUI;
    private final UserManager userManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)) return true;

        User user = userManager.getUser(p.getUniqueId());

        SGMenu titlesMenu = spiGUI.create("Owned titles", 3);

        List<Title> titles = List.of(new ZombieAssassinTitle(userManager));

        titles.forEach(title -> {
            SGButton button = new SGButton(new ItemBuilder(title.getIcon()).name("&e" + title.getName()).lore(title.getDescription(), "", (containsTitle(user, title) ? "&aUnlocked" : "&cLocked")).build())
                    .withListener(listener -> {
                        if(containsTitle(user, title)) {
                            user.setActiveTitle(title);
                            p.sendMessage(Colorize.cachedColorize("&aSuccessfully activated title &e" + title + "&a!"));
                            p.closeInventory();
                        }
                    });

            titlesMenu.addButton(button);
        });

        SGButton clearButton = new SGButton(
                new ItemBuilder(Material.BARRIER)
                        .name("&eUn-equip title")
                        .build()
        ).withListener(listener -> {
            user.setActiveTitle(null);
            p.sendMessage(Colorize.cachedColorize("&aSuccessfully un-equipped title."));
            p.closeInventory();
        });

        titlesMenu.setButton(26, clearButton);

        p.openInventory(titlesMenu.getInventory());
        return false;
    }

    private boolean containsTitle(User user, Title title) {
        return user.getOwnedTitles().contains(title);
    }
}