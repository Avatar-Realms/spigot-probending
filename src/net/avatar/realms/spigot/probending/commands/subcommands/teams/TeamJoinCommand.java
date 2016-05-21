package net.avatar.realms.spigot.probending.commands.subcommands.teams;

import net.avatar.realms.spigot.probending.commands.ProbendingCommand;
import net.avatar.realms.spigot.probending.data.Container;
import net.avatar.realms.spigot.probending.exceptions.ProbendingException;
import net.avatar.realms.spigot.probending.exceptions.ProbendingPermissionException;
import net.avatar.realms.spigot.probending.exceptions.ProbendingPlayerCommandException;
import net.avatar.realms.spigot.probending.models.ProbendingTeam;
import net.avatar.realms.spigot.probending.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Nokorbis on 6/04/2016.
 */
public class TeamJoinCommand extends ProbendingCommand {

    public TeamJoinCommand() {
        super();
        this.command = "join";
        this.aliases.add("j");
    }

    public boolean execute(CommandSender sender, List<String> args) throws ProbendingException {
        if (!(sender instanceof Player)) {
            throw new ProbendingPlayerCommandException();
        }
        if (!sender.hasPermission("probending.team.manage")) {
            throw new ProbendingPermissionException();
        }
        if (args.isEmpty()) {
            throw new ProbendingException("error.command.argument.more");
        }

        String teamName = args.remove(0);
        ProbendingTeam team = Container.getInstance().getTeam(teamName);
        if (team == null) {
            throw new ProbendingException("error.team.unexisting");
        }
        Player player = (Player) sender;
        if (!team.isInvited(player)) {
            throw new ProbendingException("error.command.join.notinvited");
        }

        team.join(player);
        String msg = Messages.get("probending.team.you.joined");
        msg = msg.replace("{TEAM}", team.getName());
        player.sendMessage(ChatColor.AQUA + msg);
        msg = Messages.get("probending.team.joined.you");
        msg = msg.replace("{PLAYER}", player.getName());
        msg = msg.replace("{TEAM}", team.getName());
        for (Player member : team.getMembers()) {
            if (member.isOnline() && !member.equals(player)) {
                member.sendMessage(ChatColor.AQUA + msg);
            }
        }
        return true;
    }

    @Override
    public void printUsage(CommandSender sender) {
        sender.sendMessage(ChatColor.AQUA + "/probending team join <TEAM_NAME>");
    }
}
