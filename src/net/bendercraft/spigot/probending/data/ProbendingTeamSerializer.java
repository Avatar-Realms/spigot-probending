package net.bendercraft.spigot.probending.data;

import com.google.gson.*;
import net.bendercraft.spigot.probending.ProbendingPlugin;
import net.bendercraft.spigot.probending.exceptions.ProbendingTeamNameException;
import net.bendercraft.spigot.probending.models.ProbendingTeam;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Type;
import java.util.UUID;

/**
 * Created by Nokorbis on 5/04/2016.
 */
public class ProbendingTeamSerializer implements JsonSerializer<ProbendingTeam>, JsonDeserializer<ProbendingTeam> {
    @Override
    public JsonElement serialize(ProbendingTeam team, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject root = new JsonObject();
        root.add("name", new JsonPrimitive(team.getName()));
        root.add("tournaments_win", new JsonPrimitive(team.getWonTournament()));
        JsonArray members = new JsonArray();
        for (Player player : team.getMembers()) {
            members.add(new JsonPrimitive(player.getUniqueId().toString()));
        }
        root.add("members", members);
        return root;
    }

    @Override
    public ProbendingTeam deserialize(JsonElement element, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject root = (JsonObject) element;
        ProbendingTeam team = new ProbendingTeam();
        String name = root.getAsJsonPrimitive("name").getAsString();
        try {
            team.setName(name);
            team.setWonTournament(root.getAsJsonPrimitive("tournaments_win").getAsInt());
            for (JsonElement member : root.getAsJsonArray("members")) {
                UUID id = UUID.fromString(member.getAsString());
                Player player = Bukkit.getPlayer(id);
                team.addMember(player);
            }
            return team;
        }
        catch (ProbendingTeamNameException e) {
            ProbendingPlugin.getInstance().getLogger().warning("A team with a invalid name has been found in the files : " + name + ". \n" + e.getMessage());
            return null;
        }
    }
}
