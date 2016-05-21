package net.bendercraft.spigot.probending.data;

import net.bendercraft.spigot.probending.models.ProbendingTeam;

import java.util.Collection;

/**
 * Created by Nokorbis on 5/04/2016.
 */
public interface IDataPersistence {

    Collection<ProbendingTeam> loadTeams();

    ProbendingTeam loadTeam(String name);

    boolean saveTeam(ProbendingTeam team);

    void saveAllTeams(Collection<ProbendingTeam> teams);

    void delete(ProbendingTeam team);
}
