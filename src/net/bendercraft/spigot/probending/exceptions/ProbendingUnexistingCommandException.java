package net.bendercraft.spigot.probending.exceptions;

/**
 * Created by Nokorbis on 5/04/2016.
 */
public class ProbendingUnexistingCommandException extends ProbendingException {

    public ProbendingUnexistingCommandException() {
        super("error.command.unexisting");
    }
}
