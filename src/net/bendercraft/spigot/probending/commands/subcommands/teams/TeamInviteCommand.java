package net.bendercraft.spigot.probending.commands.subcommands.teams;

import net.bendercraft.spigot.probending.commands.ProbendingCommand;
import net.bendercraft.spigot.probending.data.Container;
import net.bendercraft.spigot.probending.exceptions.ProbendingException;
import net.bendercraft.spigot.probending.exceptions.ProbendingPermissionException;
import net.bendercraft.spigot.probending.exceptions.ProbendingPlayerCommandException;
import net.bendercraft.spigot.probending.models.ProbendingTeam;
import net.bendercraft.spigot.probending.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Nokorbis on 6/04/2016.
 */
public class TeamInviteCommand extends ProbendingCommand {

    public TeamInviteCommand() {
        super();
        this.command = "invite";
        this.aliases.add("inv");
        this.aliases.add("i");
    }

    public boolean execute(CommandSender sender, List<String> args) throws ProbendingException {
        if (!(sender instanceof Player)) {
            throw new ProbendingPlayerCommandException();
        }
        if (!sender.hasPermission("probending.team.manage")) {
            throw new ProbendingPermissionException();
        }
        if (args.size() < 2) {
            throw new ProbendingException("error.command.argument.more");
        }

        String teamName = args.remove(0);
        ProbendingTeam team = Container.getInstance().getTeam(teamName);
        if (team == null) {
            throw new ProbendingException("error.team.unexisting");
        }
        Player player = (Player) sender;
        if (!team.isMember(player) && !player.hasPermission("probending.admin.team.invite")) {
            throw new ProbendingException("error.team.notmember");
        }

        String playerName = args.remove(0);
        Player target = Bukkit.getPlayer(playerName);
        if (target == null) {
            throw new ProbendingException("error.team.name.used");
        }
        team.invite(target);
        if (target.isOnline()) {
            String msg = Messages.get("probending.team.invited.you");
            msg = msg.replace("{TEAM}", team.getName());
            target.sendMessage(ChatColor.AQUA + msg);
        }
        String msg = Messages.get("probending.team.you.invited");
        msg = msg.replace("{PLAYER}", target.getName());
        player.sendMessage(ChatColor.AQUA + msg);
        return true;
    }

    @Override
    public void printUsage(CommandSender sender) {
        sender.sendMessage(ChatColor.AQUA + "/probending team invite <TEAM_NAME> <PLAYER_NAME>");
    }
}
