package net.cirsius.tuffvote.types;

import net.cirsius.tuffvote.VoteService;

import java.io.File;
import java.io.InputStream;

public interface PlatformTuffVote {

    public abstract File dataFolder();

    public abstract InputStream resource(String name);

    public abstract void log(String msg);

    public abstract void broadcast(String msg);

    public abstract void runCommand(String cmd);

    public default VoteService service() {
        return new VoteService();
    }

}

