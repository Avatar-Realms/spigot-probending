package net.avatar.realms.spigot.probending.models;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Nokorbis on 3/04/2016.
 */
public class ProbendingMatch {

    private ProbendingTeam redTeam;
    private ProbendingTeam blueTeam;

    private List<ProbendingRound> rounds;

    public ProbendingMatch() {
        this.rounds = new LinkedList<ProbendingRound>();
    }
}
