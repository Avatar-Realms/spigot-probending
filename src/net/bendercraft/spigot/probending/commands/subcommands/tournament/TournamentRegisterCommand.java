package net.bendercraft.spigot.probending.commands.subcommands.tournament;

import net.bendercraft.spigot.probending.commands.ProbendingCommand;
import net.bendercraft.spigot.probending.data.Container;
import net.bendercraft.spigot.probending.exceptions.ProbendingException;
import net.bendercraft.spigot.probending.exceptions.ProbendingPermissionException;
import net.bendercraft.spigot.probending.models.ProbendingTeam;
import net.bendercraft.spigot.probending.models.ProbendingTournament;
import net.bendercraft.spigot.probending.utils.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * Created by Nokorbis on 6/04/2016.
 */
public class TournamentRegisterCommand extends ProbendingCommand {

    public TournamentRegisterCommand() {
        this.command = "register";
        this.aliases.add("reg");
    }

    @Override
    public boolean execute(CommandSender sender, List<String> args) throws ProbendingException {
        if (!sender.hasPermission("probending.team.manage")) {
            throw new ProbendingPermissionException();
        }
        if (args.isEmpty()) {
            throw new ProbendingException("error.command.argument.more");
        }

        String teamName = args.remove(0);
        ProbendingTeam team = Container.getInstance().getTeam(teamName);
        ProbendingTournament tour = ProbendingTournament.getTournament();

        if (tour.isRegistered(team)) {
            throw new ProbendingException("error.command.team.registered");
        }
        tour.register(team);
        String msg = Messages.get("probending.team.registered").replace("{TEAM}", team.getName());
        sender.sendMessage(msg);
        return true;
    }

    @Override
    public void printUsage(CommandSender sender) {
        sender.sendMessage(ChatColor.AQUA + "/probending register <TEAM_NAME>");
    }
}
