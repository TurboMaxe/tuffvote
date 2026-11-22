package net.cirsius.tuffvote;

import lombok.RequiredArgsConstructor;
import net.cirsius.tuffvote.types.BaseConfig;
import net.cirsius.tuffvote.types.TuffVotePlatform;
import net.cirsius.tuffvote.util.Text;
import net.cirsius.tuffvote.util.Tuple;

import java.util.List;

@RequiredArgsConstructor
public class VoteService {

    public void handleVote(BaseConfig config, String username, String serviceName) {
        final List<String> services = config.serviceNames();
        final List<String> commands = config.commandAliases();
        final String broadcastMessage = config.broadcastMessage();
        final TuffVotePlatform tuffVotePlatform = config.tuffVotePlatform();

        if (services.stream().noneMatch(s -> s.equalsIgnoreCase(serviceName))) {
            return;
        }

        if (broadcastMessage != null && !broadcastMessage.isEmpty()) {
            String message = Text.format(broadcastMessage,
                       Tuple.of("%player%", username),
                             Tuple.of("%service%", serviceName));
            tuffVotePlatform.broadcast(message);
        }

        for (String command : commands) {
            String processedCommand = Text.format(command, Tuple.of("%player%", username));
            tuffVotePlatform.runCommand(processedCommand);
        }
    }
}
