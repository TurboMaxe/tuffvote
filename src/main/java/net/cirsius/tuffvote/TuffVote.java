package net.cirsius.tuffvote;

import lombok.RequiredArgsConstructor;
import net.cirsius.tuffvote.records.Config;
import net.cirsius.tuffvote.records.Platform;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class TuffVote {

    private final Platform platform;

    private List<String> commands = new ArrayList<>();
    private List<String> services = new ArrayList<>();
    private String broadcastMessage;


    public void enable() {
        loadConfig();
    }

    public void loadConfig() {
        try {
            File folder = platform.dataFolder();
            if (!folder.exists()) {
               if (!folder.mkdir()) {
                   platform.log("could not make data folder!");
               }
            }

            File configFile = new File(folder, "config.yml");

            if (!configFile.exists()) {
                try (InputStream in = platform.resource("config.yml")) {
                    Files.copy(in, configFile.toPath());
                }
            }

            Config config = platform.loadConfig(configFile);
            commands = config.commands();
            services = config.services();
            broadcastMessage = config.broadcastMessage();
        } catch (Exception e) {
            platform.log("couldnt load config " + e.getMessage());
        }
    }

    public void handleVote(String username, String serviceName) {
        if (!services.isEmpty() && services.stream().noneMatch(s -> s.equalsIgnoreCase(serviceName))) {
            return;
        }

        if (broadcastMessage != null && !broadcastMessage.isEmpty()) {
            String message = broadcastMessage
                    .replace("%username%", username)
                    .replace("%service%", serviceName);
            platform.broadcast(message);
        }

        for (String command : commands) {
            String processedCommand = command.replace("%username%", username);
            platform.runCommand(processedCommand);
        }
    }


}
