package net.bendercraft.spigot.probending.exceptions;

import net.bendercraft.spigot.probending.utils.Messages;

/**
 * Created by Nokorbis on 3/04/2016.
 */
public class ProbendingException extends Exception{

    /**
     * The key to use to get the error message
     */
    private String errorKey;

    public ProbendingException(String errorKey) {
        this.errorKey = errorKey;
    }

    @Override
    public String getMessage() {
        return Messages.get(errorKey);
    }
}
