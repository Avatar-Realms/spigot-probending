package net.bendercraft.spigot.probending.data;

import com.google.gson.*;

import net.bendercraft.spigot.probending.ProbendingPlugin;
import net.bendercraft.spigot.probending.exceptions.ProbendingArenaException;
import net.bendercraft.spigot.probending.models.ProbendingArena;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;


import java.lang.reflect.Type;
import java.util.UUID;

/**
 * Created by Nokorbis on 28/05/2016.
 */
public class ProbendingArenaSerializer implements JsonSerializer<ProbendingArena>, JsonDeserializer<ProbendingArena> {

    @Override
    public ProbendingArena deserialize(JsonElement element, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject root = (JsonObject) element;
        String name = root.getAsJsonPrimitive("name").getAsString();
        try {

            ProbendingArena arena = new ProbendingArena();
            arena.setName(name);

            JsonObject jsLoc = root.getAsJsonObject("blue_spawn");
            World world = Bukkit.getWorld(UUID.fromString(jsLoc.get("world").getAsString()));
            Location loc = new Location(world, jsLoc.get("x").getAsDouble(),jsLoc.get("y").getAsDouble(), jsLoc.get("z").getAsDouble());
            loc.setPitch(jsLoc.get("pitch").getAsFloat());
            loc.setYaw(jsLoc.get("yaw").getAsFloat());
            arena.setBlueSpawnLocation(loc);

            jsLoc = root.getAsJsonObject("red_spawn");
            world = Bukkit.getWorld(UUID.fromString(jsLoc.get("world").getAsString()));
            loc = new Location(world, jsLoc.get("x").getAsDouble(),jsLoc.get("y").getAsDouble(), jsLoc.get("z").getAsDouble());
            loc.setPitch(jsLoc.get("pitch").getAsFloat());
            loc.setYaw(jsLoc.get("yaw").getAsFloat());
            arena.setRedSpawnLocation(loc);

            jsLoc = root.getAsJsonObject("start_arena");
            world = Bukkit.getWorld(UUID.fromString(jsLoc.get("world").getAsString()));
            loc = new Location(world, jsLoc.get("x").getAsDouble(),jsLoc.get("y").getAsDouble(), jsLoc.get("z").getAsDouble());

            jsLoc = root.getAsJsonObject("end_arena");
            world = Bukkit.getWorld(UUID.fromString(jsLoc.get("world").getAsString()));
            Location loc2 = new Location(world, jsLoc.get("x").getAsDouble(),jsLoc.get("y").getAsDouble(), jsLoc.get("z").getAsDouble());

            arena.setArenaRegion(loc, loc2);

            return arena;
        } catch (ProbendingArenaException e) {
            ProbendingPlugin.getInstance().getLogger().warning("A arena with a invalid region has been found in the files : " + name + ". \n" + e.getMessage());
        }

        return null;
    }

    @Override
    public JsonElement serialize(ProbendingArena arena, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject root = new JsonObject();
        root.add("name", new JsonPrimitive(arena.getName()));

        JsonObject jsLoc = getJsonPlayerLocation(arena.getBlueSpawnLocation());
        root.add("blue_spawn", jsLoc);

        jsLoc = getJsonPlayerLocation(arena.getRedSpawnLocation());
        root.add("red_spawn", jsLoc);

        jsLoc = getJsonBlockLocation(arena.getArenaStartLocation());
        root.add("start_arena", jsLoc);

        jsLoc = getJsonBlockLocation(arena.getArenaEndLocation());
        root.add("end_arena", jsLoc);

        return root;
    }

    private JsonObject getJsonBlockLocation(Location loc) {
        JsonObject jsLoc;
        jsLoc = new JsonObject();
        jsLoc.add("world", new JsonPrimitive(loc.getWorld().getUID().toString()));
        jsLoc.add("x", new JsonPrimitive(loc.getBlockX()));
        jsLoc.add("y", new JsonPrimitive(loc.getBlockY()));
        jsLoc.add("z", new JsonPrimitive(loc.getBlockZ()));
        return jsLoc;
    }

    private JsonObject getJsonPlayerLocation(Location loc) {
        JsonObject jsLoc = new JsonObject();
        jsLoc.add("world", new JsonPrimitive(loc.getWorld().getUID().toString()));
        jsLoc.add("x", new JsonPrimitive(loc.getX()));
        jsLoc.add("y", new JsonPrimitive(loc.getY()));
        jsLoc.add("z", new JsonPrimitive(loc.getZ()));
        jsLoc.add("pitch", new JsonPrimitive(loc.getPitch()));
        jsLoc.add("yaw", new JsonPrimitive(loc.getYaw()));
        return jsLoc;
    }
}
