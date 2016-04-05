package net.avatar.realms.spigot.probending.commands.subcommands;

import net.avatar.realms.spigot.probending.commands.ProbendingCommand;
import net.avatar.realms.spigot.probending.data.Container;
import net.avatar.realms.spigot.probending.exceptions.ProbendingException;
import net.avatar.realms.spigot.probending.exceptions.ProbendingPermissionException;
import net.avatar.realms.spigot.probending.models.ProbendingTeam;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Nokorbis on 5/04/2016.
 */
public class TeamDeleteCommand extends ProbendingCommand {
    public TeamDeleteCommand() {
        super();
        this.command = "delete";
        this.aliases.add("del");
        this.aliases.add("d");
    }

    public boolean execute(CommandSender sender, List<String> args) throws ProbendingException {
        if (!sender.hasPermission("probending.team.manage")) {
            throw new ProbendingPermissionException();
        }
        if (args.isEmpty()) {
            throw new ProbendingException("error.command.argument.more");
        }
        String name = args.remove(0);
        ProbendingTeam team = Container.getInstance().getTeam(name);
        if (team == null) {
            throw new ProbendingException("error.team.unexisting");
        }
        if (isAllowedToDelete(sender, team)) {
            throw new ProbendingException("error.command.rename.allowed");
        }

        Container.getInstance().removeTeam(team);
        return true;
    }

    @Override
    public void printUsage(CommandSender sender) {
        sender.sendMessage(ChatColor.AQUA + "/probending team delete <NAME>");
    }

    private boolean isAllowedToDelete(CommandSender sender, ProbendingTeam team) {
        if (sender.hasPermission("probending.admin.team.delete")) {
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
