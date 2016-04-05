package net.avatar.realms.spigot.probending.commands.subcommands;

import net.avatar.realms.spigot.probending.commands.ProbendingCommand;

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
