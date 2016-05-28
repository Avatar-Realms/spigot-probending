package net.bendercraft.spigot.probending.data;

import net.bendercraft.spigot.probending.models.ProbendingTeam;

import java.util.Collection;

/**
 * Created by Nokorbis on 5/04/2016.
 */
public interface ITeamDataPersistence {

    Collection<ProbendingTeam> loadTeams();

    ProbendingTeam loadTeam(String name);

    boolean saveTeam(ProbendingTeam team);

    void saveAllTeams(Collection<ProbendingTeam> teams);

    void deleteTeam(ProbendingTeam team);
}
