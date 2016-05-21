package net.bendercraft.spigot.probending.commands.subcommands;

import net.bendercraft.spigot.probending.commands.ProbendingCommand;
import net.bendercraft.spigot.probending.commands.subcommands.teams.TeamCreateCommand;
import net.bendercraft.spigot.probending.commands.subcommands.teams.TeamDeleteCommand;
import net.bendercraft.spigot.probending.commands.subcommands.teams.TeamRenameCommand;

/**
 * Created by Nokorbis on 3/04/2016.
 */
public class TeamCommand extends ProbendingCommand {

    public TeamCommand() {
        this.command = "team";
        this.aliases.add("tm");

        addSubCommand(new TeamCreateCommand());
        addSubCommand(new TeamRenameCommand());
        addSubCommand(new TeamDeleteCommand());
    }
}
