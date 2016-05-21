package net.bendercraft.spigot.probending.commands.subcommands;

import net.bendercraft.spigot.probending.commands.ProbendingCommand;
import net.bendercraft.spigot.probending.commands.subcommands.teams.*;

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
        addSubCommand(new TeamInviteCommand());
        addSubCommand(new TeamJoinCommand());
        addSubCommand(new TeamLeaveCommand());
        addSubCommand(new TeamListCommand());
        addSubCommand(new TeamListMembersCommand());
    }
}
