package net.bendercraft.spigot.probending.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.bendercraft.spigot.probending.ProbendingPlugin;
import net.bendercraft.spigot.probending.models.ProbendingTeam;
import org.bukkit.Bukkit;

import java.io.*;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by Nokorbis on 5/04/2016.
 */
public class JsonDataPersistence implements IDataPersistence {

    private static final String CHARSET = "UTF-8";
    private static final String TEAM_FOLDER = "teams";
    private static final String EXT = ".json";

    private Gson gson;
    private File dataFolder;
    private FilenameFilter filter;

    public JsonDataPersistence(File dataFolder) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ProbendingTeam.class, new ProbendingTeamSerializer());
        builder.setPrettyPrinting();
        gson = builder.create();
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
        this.dataFolder = dataFolder;
        filter = new JsonFilenameFilter();
    }

    @Override
    public Collection<ProbendingTeam> loadTeams() {
        if (dataFolder == null || !dataFolder.exists()) {
            return Collections.emptyList();
        }
        File teamFolder = getTeamFolder();
        Collection<ProbendingTeam> teams = new LinkedList<ProbendingTeam>();

        for (File file : teamFolder.listFiles(filter)) {
            ProbendingPlugin.getInstance().getLogger().info("Team : " + file.getName());
            ProbendingTeam team = loadTeam(teamFolder, file.getName().replace(EXT, ""));
            if (team != null) {
                teams.add(team);
            }
        }

        return teams;
    }

    public ProbendingTeam loadTeam(File teamFolder, String name) {
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
    public ProbendingTeam loadTeam(String name) {
        File teamFolder = getTeamFolder();
        return loadTeam(teamFolder, name);
    }

    @Override
    public boolean saveTeam(ProbendingTeam team) {
        try {
            File teamFolder = getTeamFolder();
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
    public void delete(ProbendingTeam team) {
        File toDelete = new File(getTeamFolder(), team.getName().toLowerCase() + EXT);
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

    private static class JsonFilenameFilter implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(EXT);
        }
    }
}
