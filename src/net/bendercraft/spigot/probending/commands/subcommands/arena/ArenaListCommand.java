package net.bendercraft.spigot.probending.commands.subcommands.arena;

import net.bendercraft.spigot.probending.commands.ProbendingCommand;
import net.bendercraft.spigot.probending.data.Container;
import net.bendercraft.spigot.probending.exceptions.ProbendingException;
import net.bendercraft.spigot.probending.exceptions.ProbendingPermissionException;
import net.bendercraft.spigot.probending.exceptions.ProbendingPlayerCommandException;
import net.bendercraft.spigot.probending.models.ProbendingArena;
import net.bendercraft.spigot.probending.models.ProbendingTeam;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;

/**
 * Created by Nokorbis on 01/06/2016.
 */
public class ArenaListCommand extends ProbendingCommand {

    public ArenaListCommand() {
        this.command = "list";
        this.aliases.add("l");
    }

    @Override
    public boolean execute(CommandSender sender, List<String> args) throws ProbendingException {
        if (!(sender instanceof Player)) {
            throw new ProbendingPlayerCommandException();
        }
        if (!sender.hasPermission("probending.admin.arena.list")) {
            throw new ProbendingPermissionException();
        }

        Collection<ProbendingArena> arenas = Container.getInstance().getArenas();

        sender.sendMessage(ChatColor.BOLD + "Arenas : ");
        if (arenas.isEmpty()) {
            sender.sendMessage("   - None");
        }
        else {
            for (ProbendingArena arena : arenas) {
                sender.sendMessage("   - " + arena.getName());
            }
        }

        return true;
    }

    @Override
    public void printUsage(CommandSender sender) {
        sender.sendMessage(ChatColor.AQUA + "/probending arena list");
    }
}
