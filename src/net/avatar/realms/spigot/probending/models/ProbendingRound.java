package net.avatar.realms.spigot.probending.models;

/**
 * Created by Nokorbis on 3/04/2016.
 */
public class ProbendingRound {

    private ProbendingMatch match;
    private ProbendingTeam winner;

    public ProbendingRound(ProbendingMatch match) {
        this.match = match;
    }
}
