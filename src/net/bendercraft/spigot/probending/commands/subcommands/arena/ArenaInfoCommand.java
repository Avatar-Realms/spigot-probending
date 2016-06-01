package net.bendercraft.spigot.probending.commands.subcommands.arena;

import net.bendercraft.spigot.probending.commands.ProbendingCommand;
import net.bendercraft.spigot.probending.data.Container;
import net.bendercraft.spigot.probending.exceptions.ProbendingException;
import net.bendercraft.spigot.probending.exceptions.ProbendingPermissionException;
import net.bendercraft.spigot.probending.exceptions.ProbendingPlayerCommandException;
import net.bendercraft.spigot.probending.models.ProbendingArena;
import net.bendercraft.spigot.probending.models.ProbendingTeam;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Nokorbis on 01/06/2016.
 */
public class ArenaInfoCommand extends ProbendingCommand {

    public ArenaInfoCommand() {
        this.command = "info";
        this.aliases.add("i");
    }

    @Override
    public boolean execute(CommandSender sender, List<String> args) throws ProbendingException {
        if (!(sender instanceof Player)) {
            throw new ProbendingPlayerCommandException();
        }
        if (!sender.hasPermission("probending.command.arena.info")) {
            throw new ProbendingPermissionException();
        }
        if (args.isEmpty()) {
            throw new ProbendingException("error.command.argument.name");
        }

        String name = args.remove(0);
        ProbendingArena arena = Container.getInstance().getArena(name);
        if (arena == null) {
            throw new ProbendingException("error.arena.unexisting");
        }

        sender.sendMessage(ChatColor.BOLD + arena.getName());
        sender.sendMessage("- Red Spawn : " + formatLocation(arena.getRedSpawnLocation()));
        sender.sendMessage("- Blue Spawn : " + formatLocation(arena.getBlueSpawnLocation()));
        sender.sendMessage("- Start location : " + formatLocation(arena.getArenaStartLocation()));
        sender.sendMessage("- End location : " + formatLocation(arena.getArenaEndLocation()));

        return true;
    }

    @Override
    public void printUsage(CommandSender sender) {
        sender.sendMessage(ChatColor.AQUA + "/probending arena info <ARENA_NAME>");
    }

    private final String formatLocation(Location loc) {
        if (loc == null) {
            return "No data";
        }
        return loc.getBlockX() + ";" + loc.getBlockY() + ";" + loc.getBlockZ();
    }
}
