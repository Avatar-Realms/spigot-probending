package net.avatar.realms.spigot.probending.models;

import net.avatar.realms.spigot.probending.exceptions.ProbendingTeamNameException;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nokorbis on 3/04/2016.
 */
public class ProbendingTeam {

    /**
     * The name of the probending team. Cannot contains any white character.
     */
    private String name;

    /**
     * A set containing all the members of the team.
     */
    private Set<Player> members;

    public ProbendingTeam() {
        this.members = new HashSet<Player>();
    }

    public void setName(String name) throws ProbendingTeamNameException {
        if (name.contains(" ")) {
            throw new ProbendingTeamNameException();
        }
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Collection<Player> getMembers() {
        return this.members;
    }

    public void addMember(Player player) {
        this.members.add(player);
    }

    public void removeMember(Player player) {
        this.members.remove(player);
    }


}
