package net.avatar.realms.spigot.probending.models;

import java.util.*;

/**
 * Created by Nokorbis on 3/04/2016.
 */
public class ProbendingTournament {

    private Set<ProbendingTeam> registeredTeams;

    private Map<TournamentPhase, List<ProbendingMatch>> matches;

    private TournamentPhase currentPhase;

    public ProbendingTournament() {
        this.currentPhase = TournamentPhase.ELIMINATIONS;
        this.registeredTeams = new HashSet<ProbendingTeam>();
        matches = new HashMap<TournamentPhase, List<ProbendingMatch>>();
        matches.put(TournamentPhase.ELIMINATIONS, new LinkedList<ProbendingMatch>());
    }

    public void register(ProbendingTeam team) {

    }

    public boolean isRegistered(ProbendingTeam team) {
        return registeredTeams.contains(team);
    }

    public void unregister(ProbendingTeam team) {
        registeredTeams.remove(team);
    }
}
