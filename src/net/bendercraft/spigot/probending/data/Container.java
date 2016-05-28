package net.bendercraft.spigot.probending.data;

import net.bendercraft.spigot.probending.ProbendingPlugin;
import net.bendercraft.spigot.probending.models.ProbendingArena;
import net.bendercraft.spigot.probending.models.ProbendingTeam;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nokorbis on 5/04/2016.
 */
public class Container {

    //Singleton
    private static Container instance = null;

    private ITeamDataPersistence teamData;
    private IArenaDataPersistence arenaData;

    /**
     * A Map containing all the probending teams of the server
     * Key = The lowered case name of the Team (team.getName().toLowerCase())
     * Value = The concerned Team
     */
    private Map<String, ProbendingTeam> teams;

    /**
     * A Map containing all the probending arenas of the server
     * Key = The lowered case name of the Arena(arena.getName().toLowerCase())
     * Value = The concerned Arena
     */
    private Map<String, ProbendingArena> arenas;

    public static Container getInstance() {
        if (instance == null) {
            instance = new Container();
        }
        return instance;
    }

    private Container() {
        JsonTeamDataPersistence data = new JsonTeamDataPersistence(ProbendingPlugin.getInstance().getDataFolder());
        teamData = data;
        arenaData = data;

        teams = new HashMap<String, ProbendingTeam>();
        arenas = new HashMap<String, ProbendingArena>();

        for (ProbendingTeam team : teamData.loadTeams()) {
            //We do no use the addTeam Method because on the end, we resave the team
            teams.put(team.getName().toLowerCase(), team);
        }

        for (ProbendingArena arena : arenaData.loadArenas()) {
            arenas.put(arena.getName().toLowerCase(), arena);
        }

    }

    /**
     * Get the team that matches the name. (A lower case is applied in the method)
     * @param name The name of the team you're looking for
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
                    teamData.saveTeam(team);
                }
            }
        }
    }

    public void removeTeam(ProbendingTeam team) {
        if (team != null) {
            teamData.deleteTeam(team);
            teams.remove(team.getName().toLowerCase());
        }
    }

    /**
     * Get the arena that matches the name. (A lower case is applied in the method)
     * @param name The name of the arena you're looking for
     * @return <code>null</code> if no arena exist or if this name does not match any. a <code>Team</code> if one team is found.
     */
    public ProbendingArena getArena(String name) {
        if (arenas == null || arenas.isEmpty() || name == null || name.isEmpty()) {
            return null;
        }
        name = name.toLowerCase();
        if (arenas.containsKey(name)) {
            return arenas.get(name);
        }
        return null;
    }

    public Collection<ProbendingArena> getArenas() {
        return arenas.values();
    }

    public void addArena(ProbendingArena arena) {
        if (arena != null) {
            String name = arena.getName();
            if (name != null && !name.isEmpty()) {
                name = name.toLowerCase();
                if (!arenas.containsKey(name)) {
                    arenas.put(name, arena);
                    arenaData.saveArena(arena);
                }
            }
        }
    }

    public void removeArena(ProbendingArena arena) {
        if (arena != null) {
            arenaData.deleteArena(arena);
            arenas.remove(arena.getName().toLowerCase());
        }
    }
}
