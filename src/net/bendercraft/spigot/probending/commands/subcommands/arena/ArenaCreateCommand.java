package net.bendercraft.spigot.probending.commands.subcommands.arena;

import net.bendercraft.spigot.probending.commands.ProbendingCommand;
import net.bendercraft.spigot.probending.data.Container;
import net.bendercraft.spigot.probending.exceptions.ProbendingException;
import net.bendercraft.spigot.probending.exceptions.ProbendingPermissionException;
import net.bendercraft.spigot.probending.exceptions.ProbendingPlayerCommandException;
import net.bendercraft.spigot.probending.models.ProbendingTeam;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Nokorbis on 28/05/2016.
 */
public class ArenaCreateCommand extends ProbendingCommand {

    public ArenaCreateCommand() {
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
        sender.sendMessage(ChatColor.GREEN + "You successfully created the team : " + name);
        return true;
    }

    @Override
    public void printUsage(CommandSender sender) {
        sender.sendMessage(ChatColor.AQUA + "/probending team create <NAME>");
    }
}
