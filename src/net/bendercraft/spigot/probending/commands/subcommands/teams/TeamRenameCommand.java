package net.bendercraft.spigot.probending.commands.subcommands.teams;

import net.bendercraft.spigot.probending.commands.ProbendingCommand;
import net.bendercraft.spigot.probending.data.Container;
import net.bendercraft.spigot.probending.exceptions.ProbendingException;
import net.bendercraft.spigot.probending.exceptions.ProbendingPermissionException;
import net.bendercraft.spigot.probending.models.ProbendingTeam;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Nokorbis on 5/04/2016.
 */
public class TeamRenameCommand extends ProbendingCommand {

    public TeamRenameCommand() {
        super();
        this.command = "rename";
        this.aliases.add("name");
    }

    public boolean execute(CommandSender sender, List<String> args) throws ProbendingException {
        if (!sender.hasPermission("probending.team.manage")) {
            throw new ProbendingPermissionException();
        }
        if (args.size() < 2) {
            throw new ProbendingException("error.command.argument.more");
        }

        String oldName = args.remove(0);
        ProbendingTeam team = Container.getInstance().getTeam(oldName);
        if (team == null) {
            throw new ProbendingException("error.team.unexisting");
        }
        if (!isAllowedToRename(sender, team)) {
            throw new ProbendingException("error.command.rename.allowed");
        }

        String newName = args.remove(0);
        ProbendingTeam test = Container.getInstance().getTeam(newName);
        if (test != null) {
            throw new ProbendingException("error.team.name.used");
        }
        Container.getInstance().removeTeam(team);
        team.setName(newName);
        Container.getInstance().addTeam(team);
        sender.sendMessage(ChatColor.GREEN + "You successfully rename the team to : " + newName);
        return true;
    }

    @Override
    public void printUsage(CommandSender sender) {
        sender.sendMessage(ChatColor.AQUA + "/probending team rename <OLD_NAME> <NEW_NAME>");
    }

    private boolean isAllowedToRename(CommandSender sender, ProbendingTeam team) {
        if (sender.hasPermission("probending.admin.team.rename")) {
            return true;
        }
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (team.isMember(p)) {
                return true;
            }
        }
        return false;
    }
}
