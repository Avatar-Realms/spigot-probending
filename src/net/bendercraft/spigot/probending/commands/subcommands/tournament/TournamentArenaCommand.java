package net.bendercraft.spigot.probending.commands.subcommands.tournament;

import net.bendercraft.spigot.probending.commands.ProbendingCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * Created by Nokorbis on 06/06/2016.
 */
public class TournamentArenaCommand extends ProbendingCommand {

    public TournamentArenaCommand(){
        this.command = "arena";
        this.aliases.add("a");
    }

    @Override
    public boolean execute(CommandSender sender, List<String>  args) {

        return true;
    }
}
