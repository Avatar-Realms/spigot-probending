package net.bendercraft.spigot.probending.commands.subcommands.teams;

import net.bendercraft.spigot.probending.commands.ProbendingCommand;
import net.bendercraft.spigot.probending.data.Container;
import net.bendercraft.spigot.probending.exceptions.ProbendingException;
import net.bendercraft.spigot.probending.exceptions.ProbendingPermissionException;
import net.bendercraft.spigot.probending.exceptions.ProbendingPlayerCommandException;
import net.bendercraft.spigot.probending.models.ProbendingTeam;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;

/**
 * Created by Nokorbis on 21/05/2016.
 */
public class TeamListCommand extends ProbendingCommand {
    public TeamListCommand() {
        this.command = "list";
        this.aliases.add("li");
        this.aliases.add("l");
    }

    @Override
    public boolean execute(CommandSender sender, List<String> args) throws ProbendingException {
        if (!(sender instanceof Player)) {
            throw new ProbendingPlayerCommandException();
        }
        if (!sender.hasPermission("probending.admin.team.list")) {
            throw new ProbendingPermissionException();
        }

        Collection<ProbendingTeam> teams = Container.getInstance().getTeams();

        sender.sendMessage(ChatColor.BOLD + "Teams : ");
        if (teams.isEmpty()) {
            sender.sendMessage("   - None");
        }
        else {
            for (ProbendingTeam team : teams) {
                sender.sendMessage("   - " + team.getName());
            }
        }

        return true;
    }

    @Override
    public void printUsage(CommandSender sender) {
        sender.sendMessage(ChatColor.AQUA + "/probending team listmembers <TEAM_NAME>");
    }
}
