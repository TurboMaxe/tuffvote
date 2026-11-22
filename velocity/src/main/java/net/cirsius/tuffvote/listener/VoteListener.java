package net.cirsius.tuffvote.listener;

import com.velocitypowered.api.event.Subscribe;
import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.velocity.event.VotifierEvent;
import net.cirsius.tuffvote.TuffVotePaper;
import net.cirsius.tuffvote.config.Config;
import net.cirsius.tuffvote.types.BaseConfig;
import net.cirsius.tuffvote.types.TuffVotePlatform;

public class VoteListener {
    private final TuffVotePlatform plugin;

    public VoteListener() {
        this.plugin = TuffVotePaper.getInstance();
    }

    @Subscribe
    public void onVote(VotifierEvent event) {
        Vote vote = event.getVote();
        Config.i();
        Config.setPlugin(plugin);
        plugin.service().handleVote(new BaseConfig(
                Config.i().getServices(),
                Config.i().getCommands(),
                Config.i().getBroadcastMessage(),
                plugin
        ),  vote.getUsername(), vote.getServiceName());
    }
}