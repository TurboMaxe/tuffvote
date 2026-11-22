package net.cirsius.tuffvote.listener;

import com.vexsoftware.votifier.bungee.events.VotifierEvent;
import com.vexsoftware.votifier.model.Vote;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.cirsius.tuffvote.TuffVoteBungee;
import net.cirsius.tuffvote.config.Config;
import net.cirsius.tuffvote.types.BaseConfig;
import net.cirsius.tuffvote.types.TuffVotePlatform;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class VoteListener implements Listener {
    private final TuffVotePlatform plugin;

    public VoteListener() {
        this.plugin = TuffVoteBungee.getInstance();
    }

    @EventHandler
    public void onVote(VotifierEvent event) {
        Vote vote = event.getVote();
        plugin.service().handleVote(new BaseConfig(
                        Config.i().getServices(),
                        Config.i().getCommands(),
                        Config.i().getBroadcastMessage(),
                        plugin),
            vote.getUsername(), vote.getServiceName());
    }

}