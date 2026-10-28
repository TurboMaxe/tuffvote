package net.cirsius.tuffvote.records;

import net.cirsius.tuffvote.TuffVote;

import java.io.File;
import java.io.InputStream;

public interface Platform {

    File dataFolder();

    InputStream resource(String name);

    Config loadConfig(File configFile) throws Exception;

    void log(String msg);

    void broadcast(String msg);

    void runCommand(String cmd);
}
