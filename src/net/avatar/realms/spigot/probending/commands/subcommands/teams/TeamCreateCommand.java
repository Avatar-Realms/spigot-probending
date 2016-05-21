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

import java.util.List;

/**
 * Created by Nokorbis on 5/04/2016.
 */
public class TeamCreateCommand extends ProbendingCommand {

    public TeamCreateCommand() {
        this.command = "create";
        this.aliases.add("make");
        this.aliases.add("mk");
    }

    @Override
    public boolean execute(CommandSender sender, List<String> args) throws ProbendingException {
        if (!(sender instanceof Player)) {
            throw new ProbendingPlayerCommandException();
        }
        if (!sender.hasPermission("probending.team.manage")) {
            throw new ProbendingPermissionException();
        }
        if (args.isEmpty()) {
            throw new ProbendingException("error.command.argument.name");
        }
        String name = args.remove(0);
        ProbendingTeam team = Container.getInstance().getTeam(name);
        if (team != null) {
            throw new ProbendingException("error.team.name.used");
        }
        team = new ProbendingTeam();
        team.setName(name);
        team.addMember((Player) sender);
        Container.getInstance().addTeam(team);
        return true;
    }

    @Override
    public void printUsage(CommandSender sender) {
        sender.sendMessage(ChatColor.AQUA + "/probending team create <NAME>");
    }
}
