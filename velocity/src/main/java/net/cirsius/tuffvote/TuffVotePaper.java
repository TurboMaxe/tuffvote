package net.cirsius.tuffvote;

import com.google.inject.Inject;
import com.mojang.brigadier.tree.CommandNode;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import lombok.Getter;
import net.cirsius.tuffvote.config.Config;
import net.cirsius.tuffvote.listener.VoteListener;
import net.cirsius.tuffvote.types.TuffVotePlatform;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.slf4j.Logger;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

@Plugin(id = "tuffvote", name = "TuffVote", version = "${version}",
        authors = {"cirsius"}, dependencies = {@Dependency(id = "nuvotifier", optional = true)})
public final class TuffVotePaper implements TuffVotePlatform {

    @Getter private static TuffVotePaper instance;
    private final ProxyServer server;
    private final Path dataDirectory;
    private final Logger logger;

    @Inject
    public TuffVotePaper(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) {
        this.server = server;
        this.logger = logger;
        this.dataDirectory = dataDirectory;
        instance = this;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        server.getEventManager().register(this, new VoteListener());
        server.getCommandManager().register(new CommandMeta() {
            @Override
            public Collection<String> getAliases() {
                return List.of(
                        "tuffvotereload"
                );
            }

            @Override
            public Collection<CommandNode<CommandSource>> getHints() {
                return List.of();
            }

            @Override
            public @Nullable Object getPlugin() {
                return null;
            }
        }, new ReloadCommand());
    }

    @Override
    public File dataFolder() {
        return dataDirectory.toFile();
    }

    @Override
    public InputStream resource(String name) {
        return getClass().getClassLoader().getResourceAsStream(name);
    }

    @Override
    public void log(String msg) {
        logger.error(msg);
    }

    @Override
    public void broadcast(String msg) {
        Component message = LegacyComponentSerializer.legacyAmpersand().deserialize(msg);
        server.getAllPlayers().forEach(
                player -> player.sendMessage(message)
        );
        server.getConsoleCommandSource().sendMessage(message);
    }

    @Override
    public void runCommand(String cmd) {
        server.getCommandManager().executeAsync(server.getConsoleCommandSource(), cmd);
    }

    private class ReloadCommand implements SimpleCommand {

        @Override
        public void execute(Invocation invocation) {
            if (invocation instanceof CommandSource source) {
                source.sendRichMessage("<yellow> config reloaded </yellow>");
            }
            Config.i().reload();
        }
    }

}