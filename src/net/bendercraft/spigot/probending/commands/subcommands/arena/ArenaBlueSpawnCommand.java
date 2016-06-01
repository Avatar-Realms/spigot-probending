package net.bendercraft.spigot.probending.commands.subcommands.arena;

import net.bendercraft.spigot.probending.commands.ProbendingCommand;
import net.bendercraft.spigot.probending.data.Container;
import net.bendercraft.spigot.probending.exceptions.ProbendingException;
import net.bendercraft.spigot.probending.exceptions.ProbendingPermissionException;
import net.bendercraft.spigot.probending.models.ProbendingArena;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Nokorbis on 01/06/2016.
 */
public class ArenaBlueSpawnCommand extends ProbendingCommand {

    public ArenaBlueSpawnCommand() {
        this.command = "setbluespawnlocation";
        this.aliases.add("blue");
        this.aliases.add("bluespawn");
        this.aliases.add("bluelocation");
        this.aliases.add("setblue");
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
        arena.setBlueSpawnLocation(player.getLocation());
        Container.getInstance().removeArena(arena);
        Container.getInstance().addArena(arena);
        sender.sendMessage(ChatColor.GREEN + "You successfully set the blue spawn location for the arena : " + name);
        return true;
    }

    @Override
    public void printUsage(CommandSender sender) {
        sender.sendMessage(ChatColor.AQUA + "/probending arena redspawn <ARENA_NAME>");
    }
}
