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
public class TeamListMembersCommand extends ProbendingCommand {

    public TeamListMembersCommand() {
        this.command = "listmembers";
        this.aliases.add("lm");
    }

    @Override
    public boolean execute(CommandSender sender, List<String> args) throws ProbendingException {
        if (!(sender instanceof Player)) {
            throw new ProbendingPlayerCommandException();
        }
        if (!sender.hasPermission("probending.command.team.listmembers")) {
            throw new ProbendingPermissionException();
        }
        if (args.isEmpty()) {
            throw new ProbendingException("error.command.argument.name");
        }

        String name = args.remove(0);
        ProbendingTeam team = Container.getInstance().getTeam(name);
        if (team == null) {
            throw new ProbendingException("error.team.unexisting");
        }

        sender.sendMessage(ChatColor.BOLD + "Team members : ");
        if (team.getMembers().isEmpty()) {
            sender.sendMessage("   - No members. This team may need to be deleted.");
        }
        else {
            for (Player member : team.getMembers()) {
                sender.sendMessage("   - " + member.getName());
            }
        }

        return true;

    }

    @Override
    public void printUsage(CommandSender sender) {
        sender.sendMessage(ChatColor.AQUA + "/probending team create <NAME>");
    }
}
