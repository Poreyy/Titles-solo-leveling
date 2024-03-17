package me.poreyy.titles;

import com.samjakob.spigui.SpiGUI;
import lombok.Getter;
import me.poreyy.titles.commands.TitleCommand;
import me.poreyy.titles.listeners.ChatListener;
import me.poreyy.titles.title.TitleListener;
import me.poreyy.titles.title.titles.ZombieAssassinTitle;
import me.poreyy.titles.user.UserListener;
import me.poreyy.titles.user.UserManager;
import me.poreyy.titles.user.data.YamlDataHandler;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class TitlesPlugin extends JavaPlugin {

    private UserManager userManager;

    @Override
    public void onEnable() {
        registerManagers();
        registerListeners();
        registerCommands();
    }

    private void registerManagers() {
        this.userManager = new UserManager(new YamlDataHandler(this));
    }

    private void registerListeners() {
        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new UserListener(userManager), this);
        pluginManager.registerEvents(new ChatListener(userManager), this);
        pluginManager.registerEvents(new TitleListener(new ZombieAssassinTitle(userManager)), this);
    }

    private void registerCommands() {
        getCommand("title").setExecutor(new TitleCommand(new SpiGUI(this), userManager));
    }

    @Override
    public void onDisable() {
        getServer().getOnlinePlayers().forEach(player -> userManager.saveUserData(player.getUniqueId()));
    }
}