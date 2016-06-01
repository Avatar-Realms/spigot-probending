package net.bendercraft.spigot.probending.commands.subcommands.arena;

import net.bendercraft.spigot.probending.commands.ProbendingCommand;
import net.bendercraft.spigot.probending.data.Container;
import net.bendercraft.spigot.probending.exceptions.ProbendingException;
import net.bendercraft.spigot.probending.exceptions.ProbendingPermissionException;
import net.bendercraft.spigot.probending.models.ProbendingArena;
import net.bendercraft.spigot.probending.models.ProbendingTeam;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * Created by Nokorbis on 01/06/2016.
 */
public class ArenaDeleteCommand extends ProbendingCommand {

    public ArenaDeleteCommand() {
        this.command = "delete";
        this.aliases.add("del");
        this.aliases.add("remove");
        this.aliases.add("rm");
    }

    @Override
    public boolean execute(CommandSender sender, List<String> args) throws ProbendingException {
        if (!sender.hasPermission("probending.arena.manage")) {
            throw new ProbendingPermissionException();
        }
        if (args.isEmpty()) {
            throw new ProbendingException("error.command.argument.more");
        }
        String name = args.remove(0);
        ProbendingArena arena = Container.getInstance().getArena(name);
        if (arena == null) {
            throw new ProbendingException("error.arena.unexisting");
        }

        Container.getInstance().removeArena(arena);
        sender.sendMessage(ChatColor.YELLOW + "You successfully deleted the arena : " + name);
        return true;
    }

    @Override
    public void printUsage(CommandSender sender) {
        sender.sendMessage(ChatColor.AQUA + "/probending arena delete <NAME>");
    }
}
