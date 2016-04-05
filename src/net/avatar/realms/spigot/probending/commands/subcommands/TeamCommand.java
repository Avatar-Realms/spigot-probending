package net.avatar.realms.spigot.probending.commands.subcommands;

import net.avatar.realms.spigot.probending.commands.ProbendingCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * Created by Nokorbis on 3/04/2016.
 */
public class TeamCommand extends ProbendingCommand {

    public TeamCommand() {
        this.command = "team";
        this.aliases.add("tm");

        addSubCommand(new TeamCreateCommand());
        addSubCommand(new TeamRenameCommand());
    }

    @Override
    public void printUsage(CommandSender sender, boolean permission) {

    }
}
