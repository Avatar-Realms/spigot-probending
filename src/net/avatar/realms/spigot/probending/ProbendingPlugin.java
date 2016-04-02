package net.avatar.realms.spigot.probending;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Nokorbis on 3/04/2016.
 */
public class ProbendingPlugin extends JavaPlugin {

    private static ProbendingPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static ProbendingPlugin getInstance() {
        return instance;
    }
}
