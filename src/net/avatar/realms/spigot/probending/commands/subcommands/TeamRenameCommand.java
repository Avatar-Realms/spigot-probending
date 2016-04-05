package net.avatar.realms.spigot.probending.commands.subcommands;

import net.avatar.realms.spigot.probending.commands.ProbendingCommand;
import net.avatar.realms.spigot.probending.exceptions.ProbendingException;
import net.avatar.realms.spigot.probending.exceptions.ProbendingPermissionException;
import net.avatar.realms.spigot.probending.exceptions.ProbendingPlayerCommandException;
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
        if (!(sender instanceof Player)) {
            throw new ProbendingPlayerCommandException();
        }
        if (!sender.hasPermission("probending.team.manage")) {
            throw new ProbendingPermissionException();
        }
        return true;
    }

    @Override
    public void printUsage(CommandSender sender, boolean permission) {

    }
}
