package net.cirsius.tuffvote.records;


import java.util.List;

public record Config(List<String> commands, List<String> services, String broadcastMessage) {



}
