package net.bendercraft.spigot.probending.models;

import java.util.*;

/**
 * Created by Nokorbis on 3/04/2016.
 */
public class ProbendingTournament {

    private static ProbendingTournament instance = null;

    private Set<ProbendingTeam> registeredTeams;

    private Map<TournamentPhase, List<ProbendingMatch>> matches;

    private TournamentPhase currentPhase;

    public static ProbendingTournament getTournament() {
        if (instance == null) {
            instance = new ProbendingTournament();
        }
        return instance;
    }

    private ProbendingTournament() {
        this.currentPhase = TournamentPhase.ELIMINATIONS;
        this.registeredTeams = new HashSet<ProbendingTeam>();
        matches = new HashMap<TournamentPhase, List<ProbendingMatch>>();
        matches.put(TournamentPhase.ELIMINATIONS, new LinkedList<ProbendingMatch>());
    }

    public void register(ProbendingTeam team) {
        if (team != null) {
            registeredTeams.add(team);
        }
    }

    public void unregister(ProbendingTeam team) {
        if (registeredTeams != null) {
            registeredTeams.remove(team);
        }
    }

    public boolean isRegistered(ProbendingTeam team) {
        if (registeredTeams == null) {
            return false;
        }
        return registeredTeams.contains(team);
    }
}
