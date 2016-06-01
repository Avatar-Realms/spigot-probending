package net.bendercraft.spigot.probending.commands.subcommands.arena;

import net.bendercraft.spigot.probending.commands.ProbendingCommand;

/**
 * Created by Nokorbis on 28/05/2016.
 */
public class ArenaCommand extends ProbendingCommand {

    public ArenaCommand() {
        this.command = "arena";
        this.aliases.add("ar");
        this.aliases.add("a");

        this.addSubCommand(new ArenaCreateCommand());
        this.addSubCommand(new ArenaDeleteCommand());
        this.addSubCommand(new ArenaInfoCommand());
        this.addSubCommand(new ArenaListCommand());
        this.addSubCommand(new ArenaRegionCommand());
        this.addSubCommand(new ArenaBlueSpawnCommand());
        this.addSubCommand(new ArenaRedSpawnCommand());
    }
}
