package net.avatar.realms.spigot.probending;

import net.avatar.realms.spigot.probending.commands.ProbendingCommands;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Nokorbis on 3/04/2016.
 */
public class ProbendingPlugin extends JavaPlugin {

    private static ProbendingPlugin instance;

    private ProbendingCommands commands;

    @Override
    public void onEnable() {
        instance = this;
        commands = new ProbendingCommands();
        getCommand("probending").setExecutor(commands);
        getCommand("probending").setTabCompleter(commands);
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static ProbendingPlugin getInstance() {
        return instance;
    }
}
