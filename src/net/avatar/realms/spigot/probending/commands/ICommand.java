package net.avatar.realms.spigot.probending.commands;

import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * Created by Nokorbis on 3/04/2016.
 */
public interface ICommand {

    boolean execute(CommandSender sender, List<String> args) throws Exception;

    void printUsage(CommandSender sender);

    void printUsage(CommandSender sender, boolean permission);

    boolean isCommand(String command);

    String getCommand();

    /**
     * Get all possibles values for a given command and given arguments
     *
     * @param sender
     *            The sender of the command
     * @param args
     *            The args that the sender sent (without the first arg being the
     *            subcommand)
     * @return List<String> of possible values
     */
    public List<String> autoComplete(CommandSender sender, List<String> args);
}
