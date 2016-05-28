package net.bendercraft.spigot.probending.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.bendercraft.spigot.probending.ProbendingPlugin;
import net.bendercraft.spigot.probending.models.ProbendingArena;
import net.bendercraft.spigot.probending.models.ProbendingTeam;

import java.io.*;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by Nokorbis on 5/04/2016.
 */
public class JsonTeamDataPersistence implements ITeamDataPersistence, IArenaDataPersistence {

    private static final String CHARSET = "UTF-8";
    private static final String TEAM_FOLDER = "teams";
    private static final String ARENA_FOLDER = "arenas";
    private static final String EXT = ".json";

    private Gson gson;
    private File dataFolder;
    private File teamFolder;
    private File arenaFolder;
    private FilenameFilter filter;

    public JsonTeamDataPersistence(File dataFolder) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ProbendingTeam.class, new ProbendingTeamSerializer());
        builder.registerTypeAdapter(ProbendingArena.class, new ProbendingArenaSerializer());
        builder.setPrettyPrinting();
        gson = builder.create();
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
        this.dataFolder = dataFolder;
        teamFolder = getTeamFolder();
        arenaFolder = getArenaFolder();
        filter = new JsonFilenameFilter();
    }

    @Override
    public Collection<ProbendingTeam> loadTeams() {
        if (teamFolder == null || !teamFolder.exists()) {
            return Collections.emptyList();
        }
        Collection<ProbendingTeam> teams = new LinkedList<ProbendingTeam>();

        for (File file : teamFolder.listFiles(filter)) {
            ProbendingTeam team = loadTeam(file.getName().replace(EXT, ""));
            if (team != null) {
                teams.add(team);
            }
        }

        return teams;
    }

    @Override
    public ProbendingTeam loadTeam(String name) {
        File file = new File(teamFolder, name + EXT);
        if (!file.exists() || !file.isFile()) {
            return null;
        }

        try {
            InputStream is = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(is, CHARSET);
            ProbendingTeam team = gson.fromJson(reader, ProbendingTeam.class);
            reader.close();
            return team;
        }
        catch (FileNotFoundException e) {
            ProbendingPlugin.getInstance().getLogger().warning("Was not able to find a file while loading a team : " + name);
        }
        catch (UnsupportedEncodingException e) {
            ProbendingPlugin.getInstance().getLogger().warning("Was not able to handle the charset " + CHARSET);
        } catch (IOException e) {
            ProbendingPlugin.getInstance().getLogger().warning("Was not able to read a file while loading a team");
        }
        return null;
    }

    @Override
    public boolean saveTeam(ProbendingTeam team) {
        try {
            File file = new File(teamFolder, team.getName().toLowerCase() + EXT);
            if (!file.exists()) {
                file.createNewFile();
            }
            OutputStream os = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(os, CHARSET);
            String json = gson.toJson(team);
            writer.write(json);
            writer.close();
            return true;
        } catch (FileNotFoundException e) {
            ProbendingPlugin.getInstance().getLogger().severe("Was not able to find a file while saving a team : " + team.getName());
            return false;
        } catch (IOException e) {
            ProbendingPlugin.getInstance().getLogger().severe("Was not able to create a file while saving a command block : " + team.getName());
            return false;
        }
    }

    @Override
    public void saveAllTeams(Collection<ProbendingTeam> teams) {
        teams.forEach(this::saveTeam);
    }

    @Override
    public void deleteTeam(ProbendingTeam team) {
        File toDelete = new File(teamFolder, team.getName().toLowerCase() + EXT);
        if (toDelete.exists()) {
            toDelete.delete();
        }
    }

    private File getTeamFolder() {
        File teamFolder = new File(dataFolder, TEAM_FOLDER);
        if (!teamFolder.exists()) {
            teamFolder.mkdirs();
        }
        return teamFolder;
    }

    @Override
    public Collection<ProbendingArena> loadArenas() {
        if (arenaFolder == null || !arenaFolder.exists()) {
            return Collections.emptyList();
        }
        Collection<ProbendingArena> arenas = new LinkedList<ProbendingArena>();

        for (File file : teamFolder.listFiles(filter)) {
            ProbendingArena arena = loadArena(file.getName().replace(EXT, ""));
            if (arena != null) {
                arenas.add(arena);
            }
        }

        return arenas;
    }

    @Override
    public ProbendingArena loadArena(String name) {
        File file = new File(arenaFolder, name + EXT);
        if (!file.exists() || !file.isFile()) {
            return null;
        }

        try {
            InputStream is = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(is, CHARSET);
            ProbendingArena arena = gson.fromJson(reader, ProbendingArena.class);
            reader.close();
            return arena;
        }
        catch (FileNotFoundException e) {
            ProbendingPlugin.getInstance().getLogger().warning("Was not able to find a file while loading a arena : " + name);
        }
        catch (UnsupportedEncodingException e) {
            ProbendingPlugin.getInstance().getLogger().warning("Was not able to handle the charset " + CHARSET);
        } catch (IOException e) {
            ProbendingPlugin.getInstance().getLogger().warning("Was not able to read a file while loading a arena");
        }
        return null;
    }

    @Override
    public boolean saveArena(ProbendingArena arena) {
        try {
            File file = new File(arenaFolder, arena.getName().toLowerCase() + EXT);
            if (!file.exists()) {
                file.createNewFile();
            }
            OutputStream os = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(os, CHARSET);
            String json = gson.toJson(arena);
            writer.write(json);
            writer.close();
            return true;
        } catch (FileNotFoundException e) {
            ProbendingPlugin.getInstance().getLogger().severe("Was not able to find a file while saving a arena : " + arena.getName());
            return false;
        } catch (IOException e) {
            ProbendingPlugin.getInstance().getLogger().severe("Was not able to create a file while saving a command block : " + arena.getName());
            return false;
        }
    }

    @Override
    public void saveAllArenas(Collection<ProbendingArena> arenas) {
        arenas.forEach(this::saveArena);
    }

    @Override
    public void deleteArena(ProbendingArena arena) {
        File toDelete = new File(teamFolder, arena.getName().toLowerCase() + EXT);
        if (toDelete.exists()) {
            toDelete.delete();
        }
    }

    private File getArenaFolder() {
        File arenaFolder = new File(dataFolder, ARENA_FOLDER);
        if (!arenaFolder.exists()) {
            arenaFolder.mkdirs();
        }
        return arenaFolder;
    }

    private static class JsonFilenameFilter implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(EXT);
        }
    }
}
