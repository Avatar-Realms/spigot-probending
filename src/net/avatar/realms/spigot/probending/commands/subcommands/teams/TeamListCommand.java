package net.avatar.realms.spigot.probending.commands.subcommands.teams;

import net.avatar.realms.spigot.probending.commands.ProbendingCommand;
import net.avatar.realms.spigot.probending.data.Container;
import net.avatar.realms.spigot.probending.exceptions.ProbendingException;
import net.avatar.realms.spigot.probending.exceptions.ProbendingPermissionException;
import net.avatar.realms.spigot.probending.exceptions.ProbendingPlayerCommandException;
import net.avatar.realms.spigot.probending.models.ProbendingTeam;
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
        if (!sender.hasPermission("probending.command.team.list")) {
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
        sender.sendMessage(ChatColor.AQUA + "/probending team create <NAME>");
    }
}
