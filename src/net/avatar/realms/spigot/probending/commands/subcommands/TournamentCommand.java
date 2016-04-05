package net.avatar.realms.spigot.probending.commands.subcommands;

import net.avatar.realms.spigot.probending.commands.ProbendingCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * Created by Nokorbis on 3/04/2016.
 */
public class TournamentCommand extends ProbendingCommand {

    public TournamentCommand() {
        this.command = "tournament";
        this.aliases.add("tour");
    }

    @Override
    public boolean execute(CommandSender sender, List<String> args) {
        return false;
    }

    @Override
    public void printUsage(CommandSender sender, boolean permission) {

    }
}
