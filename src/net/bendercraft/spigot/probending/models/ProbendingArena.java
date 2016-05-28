package net.bendercraft.spigot.probending.models;

import net.bendercraft.spigot.probending.exceptions.ProbendingArenaException;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Nokorbis on 22/05/2016.
 */
public class ProbendingArena {

    private String name;

    private Location blueSpawnLocation;
    private Location redSpawnLocation;

    //Only used for storage purpose
    private Location arenaStartLocation;
    private Location arenaEndLocation;

    //Used to help regeneration / Not stored
    private List<Location> earthbendablesBlocks;
    private List<Location> waterbendablesBlocks;

    public ProbendingArena() {
        earthbendablesBlocks = new LinkedList<Location>();
        waterbendablesBlocks = new LinkedList<Location>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArenaRegion(Location arenaStartLocation, Location arenaEndLocation) throws ProbendingArenaException {
        if (arenaStartLocation == null || arenaEndLocation == null) {
            throw new ProbendingArenaException("error.arena.location.invalid");
        }
        if (!arenaStartLocation.getWorld().equals(arenaEndLocation.getWorld())) {
            throw new ProbendingArenaException(("error.arena.location.notsameworld"));
        }

        this.arenaStartLocation = arenaStartLocation;
        this.arenaEndLocation = arenaEndLocation;

        earthbendablesBlocks.clear();
        waterbendablesBlocks.clear();

        //There might be a better way to do it
        final int minX = (arenaStartLocation.getBlockX() < arenaEndLocation.getBlockX()) ? arenaStartLocation.getBlockX() : arenaEndLocation.getBlockX();
        final int maxX = (arenaStartLocation.getBlockX() > arenaEndLocation.getBlockX()) ? arenaStartLocation.getBlockX() : arenaEndLocation.getBlockX();
        final int minY = (arenaStartLocation.getBlockY() < arenaEndLocation.getBlockY()) ? arenaStartLocation.getBlockY() : arenaEndLocation.getBlockY();
        final int maxY = (arenaStartLocation.getBlockY() > arenaEndLocation.getBlockY()) ? arenaStartLocation.getBlockY() : arenaEndLocation.getBlockY();
        final int minZ = (arenaStartLocation.getBlockZ() < arenaEndLocation.getBlockZ()) ? arenaStartLocation.getBlockZ() : arenaEndLocation.getBlockZ();
        final int maxZ = (arenaStartLocation.getBlockZ() > arenaEndLocation.getBlockZ()) ? arenaStartLocation.getBlockZ() : arenaEndLocation.getBlockZ();

        Location loc = arenaStartLocation.clone();
        for (int i = minX ; i < maxX ; i++) {
            loc.setX(i);
            for (int j = minY ; j < maxY ; j++) {
                loc.setY(j);
                for (int k = minZ; k < maxZ ; k++) {
                    loc.setZ(k);
                    Block block = loc.getBlock();
                    //If this is an earth block
                    if (block.getType() == Material.STONE) {
                        byte data = block.getData();
                        if (data != 0x2 && data != 0x4 && data != 0x6) {
                            earthbendablesBlocks.add(block.getLocation());
                        }
                    }
                    else if (block.getType() == Material.WATER ||block.getType() == Material.STATIONARY_WATER) {
                        waterbendablesBlocks.add(block.getLocation());
                    }
                }
            }
        }
    }

    public void setRedSpawnLocation(Location redLocation) {
        this.redSpawnLocation = redLocation;
    }

    public void setBlueSpawnLocation(Location blueLocation) {
        this.blueSpawnLocation = blueLocation;
    }

    public final Location getBlueSpawnLocation() {
        return blueSpawnLocation;
    }

    public final Location getRedSpawnLocation() {
        return redSpawnLocation;
    }

    public final Location getArenaStartLocation() {
        return arenaStartLocation;
    }

    public final Location getArenaEndLocation() {
        return arenaEndLocation;
    }

    public String getName() {
        return name;
    }

    public void regenArena() {
        for (Location loc : earthbendablesBlocks) {
            loc.getBlock().setType(Material.STONE);
        }
        for (Location loc : waterbendablesBlocks) {
            loc.getBlock().setType(Material.STATIONARY_WATER);
        }
    }
}
