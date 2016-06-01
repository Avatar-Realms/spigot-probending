package net.bendercraft.spigot.probending.commands.subcommands.arena;

import net.bendercraft.spigot.probending.commands.ProbendingCommand;
import net.bendercraft.spigot.probending.data.Container;
import net.bendercraft.spigot.probending.exceptions.ProbendingException;
import net.bendercraft.spigot.probending.exceptions.ProbendingPermissionException;
import net.bendercraft.spigot.probending.models.ProbendingArena;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Nokorbis on 01/06/2016.
 */
public class ArenaRegionCommand extends ProbendingCommand {

    public ArenaRegionCommand() {
        this.command = "setregion";
        this.aliases.add("region");
        this.aliases.add("reg");
        this.aliases.add("rg");
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

        Player player = (Player) sender;
        Location startLocation = getLocation(player, args);
        Location endLocation = getLocation(player, args);
        arena.setArenaRegion(startLocation, endLocation);
        Container.getInstance().removeArena(arena); // To make it saved
        Container.getInstance().addArena(arena);

        sender.sendMessage(ChatColor.GREEN + "You successfully set the red spawn location for the arena : " + name);
        return true;
    }

    @Override
    public void printUsage(CommandSender sender) {
        sender.sendMessage(ChatColor.AQUA + "/probending arena region <ARENA_NAME> <START_X> <START_Y> <START_Z> <END_X> <END_Y> <END_Z>");
    }

    private Location getLocation(Player player, List<String> args) throws ProbendingException {
        if (player == null ||args == null || args.size() < 3) {
            throw new ProbendingException("error.arena.region.parse");
        }

        try {
            int x = Integer.parseInt(args.remove(0));
            int y = Integer.parseInt(args.remove(0));
            int z = Integer.parseInt(args.remove(0));
            return new Location(player.getWorld(), x, y, z);
        }
        catch (NumberFormatException ex) {
            throw new ProbendingException("error.arena.region.parse");
        }
    }

}
