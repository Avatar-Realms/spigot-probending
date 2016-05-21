package net.bendercraft.spigot.probending.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Nokorbis on 3/04/2016.
 */
public class ProbendingCommands implements CommandExecutor, TabCompleter {

    private List<ICommand> commands;

    public ProbendingCommands() {
        this.commands = new LinkedList<ICommand>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command label, String s, String[] args) {
        if (sender == null) {
            return false;
        }
        if (args.length < 1) {
            return false;
        }

        List<String> argList = new LinkedList<>(Arrays.asList(args));
        String subCommand = argList.remove(0);

        for (ICommand command : this.commands) {
            if (command.isCommand(subCommand)) {
                try {
                    return command.execute(sender, argList);
                }
                catch (Exception e) {
                    sender.sendMessage(ChatColor.RED + e.getMessage());
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command label, String s, String[] args) {
        List<String> result = new ArrayList<String>();
        if (args.length == 0) {
            result.add("probending");
        }
        else if (args.length == 1) {
            List<String> values = new LinkedList<String>();
            for (ICommand command : this.commands) {
                values.add(command.getCommand());
            }
            result.add(autoCompleteParameter(args[0], values));
        }
        else {
            List<String> argList = new LinkedList<String>(Arrays.asList(args));
            String sub = argList.remove(0);
            for (ICommand command : this.commands) {
                if (command.isCommand(sub)) {
                    result.add(autoCompleteParameter(argList.get(0), command.autoComplete(sender, argList)));
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Choose the best available value for the autocompletion
     *
     * @param start
     *            What was the parameter sent
     * @param values
     *            What are the possible values
     * @return the best value
     */
    private String autoCompleteParameter(String start, List<String> values) {
        if (start == null || start.isEmpty()) {
            return " ";
        }

        List<String> valids = new LinkedList<String>();
        if (values == null) {
            return start;
        }

        for (String value : values) {
            if (value.toLowerCase().startsWith(start.toLowerCase())) {
                valids.add(value);
            }
        }
        if (valids.size() < 1) {
            return start;
        } else if (valids.size() == 1) {
            return valids.get(0);
        } else {
            String base = valids.get(0);
            valids.remove(0);
            StringBuilder builder = new StringBuilder();
            int i = 0;
            boolean done = false;
            while (!done) {
                if (i >= base.length()) {
                    break;
                }
                boolean same = true;
                char c = base.charAt(i);
                for (String other : valids) {
                    if (other.charAt(i) != c) {
                        same = false;
                        done = true;
                        break;
                    }
                }
                if (same) {
                    builder.append(c);
                }
                i++;
            }
            return builder.toString();
        }
    }
}
