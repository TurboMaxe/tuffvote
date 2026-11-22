package net.cirsius.tuffvote.types;

import java.util.List;

public record BaseConfig(
        List<String> serviceNames,
        List<String> commandAliases,
        String broadcastMessage,
        TuffVotePlatform tuffVotePlatform
) {}
