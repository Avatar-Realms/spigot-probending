package net.avatar.realms.spigot.probending.commands;

import net.avatar.realms.spigot.probending.exceptions.ProbendingException;
import net.avatar.realms.spigot.probending.exceptions.ProbendingUnexistingCommandException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Nokorbis on 3/04/2016.
 */
public abstract class ProbendingCommand implements ICommand {

    protected String command;
    protected List<String> aliases;

    private List<ProbendingCommand> subCommands;

    public ProbendingCommand() {
        this.aliases = new LinkedList<String>();
        this.subCommands = new LinkedList<ProbendingCommand>();
    }

    @Override
    public boolean isCommand(String cmd) {
        if (this.command.equalsIgnoreCase(cmd)) {
            return true;
        }

        for (String alias : this.aliases) {
            if (alias.equalsIgnoreCase(cmd)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean execute (CommandSender sender, List<String> args) throws ProbendingException {
        //Default implementation : Check for subcommands
        if (args.isEmpty()) {
            throw new ProbendingException("error.command.argument.more");
        }
        String subCommand = args.remove(0);

        for (ProbendingCommand command : this.subCommands) {
            if (command.isCommand(subCommand)) {
                return command.execute(sender, args);
            }
        }
        throw new ProbendingUnexistingCommandException();
    }

    protected Player getPlayer(String name) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            if (player.getName().equalsIgnoreCase(name)) {
                return player;
            }
        }
        return null;
    }

    @Override
    public void printUsage(CommandSender sender) {
        printUsage(sender, true);
    }

    @Override
    public String getCommand() {
        return this.command;
    }

    @Override
    public List<String> autoComplete(CommandSender sender, List<String> args) {
        return new LinkedList<String>();
    }

    protected void addSubCommand(ProbendingCommand subCommand) {
        this.subCommands.add(subCommand);
    }
}
