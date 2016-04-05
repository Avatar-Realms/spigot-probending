package net.avatar.realms.spigot.probending.data;

import net.avatar.realms.spigot.probending.ProbendingPlugin;
import net.avatar.realms.spigot.probending.models.ProbendingTeam;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nokorbis on 5/04/2016.
 */
public class Container {

    //Singleton
    private static Container instance = null;

    private IDataPersistence data;

    /**
     * A Map containing all the probending teams of the server
     * Key = The lowered case name of the Team (team.getName().toLowerCase())
     * Value = The concerned Team
     */
    private Map<String, ProbendingTeam> teams;

    public static Container getInstance() {
        if (instance == null) {
            instance = new Container();
        }
        return instance;
    }

    private Container() {
        data = new JsonDataPersistence(ProbendingPlugin.getInstance().getDataFolder());
        teams = new HashMap<String, ProbendingTeam>();
        for (ProbendingTeam team : data.loadTeams()) {
            //We do no use the addTeam Method because on the end, we resave the team
            teams.put(team.getName().toLowerCase(), team);
        }
    }

    /**
     * Get the team that matches the name. (A lower case is applied in the method)
     * @param name The name of the you're looking for
     * @return <code>null</code> if no team exist or if this name does not match any. a <code>Team</code> if one team is found.
     */
    public ProbendingTeam getTeam(String name) {
        if (teams == null || teams.isEmpty() || name == null || name.isEmpty()) {
            return null;
        }
        name = name.toLowerCase();
        if (teams.containsKey(name)) {
            return teams.get(name);
        }
        return null;
    }

    public Collection<ProbendingTeam> getTeams() {
        return teams.values();
    }

    public void addTeam(ProbendingTeam team) {
        if (team != null) {
            String name = team.getName();
            if (name != null && !name.isEmpty()) {
                name = name.toLowerCase();
                if (!teams.containsKey(name)) {
                    teams.put(name, team);
                    data.saveTeam(team);
                }
            }
        }
    }
}
