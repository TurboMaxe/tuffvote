package net.cirsius.tuffvote.command;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import net.cirsius.tuffvote.TuffVote;
import net.md_5.bungee.api.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

public class ReloadCommand implements VoteCommand {

    private static final BiConsumer<TuffVote, Object> cmd = (tuffVote, sender) -> {
            tuffVote.loadConfig();
            if (sender instanceof CommandSender sndr) sndr.sendMessage("config reloaded");
            if (sender instanceof CommandSource source) source.sendRichMessage("<yellow> config reloaded");
            throw new IllegalArgumentException("Only bungeecord and velocity are supported!");
    };

    public static BiConsumer<TuffVote, Object> cmd() {
        return cmd;
    }
}
