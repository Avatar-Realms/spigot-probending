package net.bendercraft.spigot.probending.data;

import net.bendercraft.spigot.probending.models.ProbendingArena;

import java.util.Collection;

/**
 * Created by Nokorbis on 28/05/2016.
 */
public interface IArenaDataPersistence {

    Collection<ProbendingArena> loadArenas();

    ProbendingArena loadArena(String name);

    boolean saveArena(ProbendingArena arena);

    void saveAllArenas(Collection<ProbendingArena> arenas);

    void deleteArena(ProbendingArena arena);
}
