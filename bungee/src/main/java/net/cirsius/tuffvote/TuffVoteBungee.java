package net.cirsius.tuffvote;

import lombok.Getter;
import net.cirsius.tuffvote.config.Config;
import net.cirsius.tuffvote.listener.VoteListener;
import net.cirsius.tuffvote.types.TuffVotePlatform;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;
import java.io.InputStream;

public class TuffVoteBungee extends Plugin implements TuffVotePlatform {

    @Getter private static TuffVoteBungee instance;

    public TuffVoteBungee() {
        instance = this;
    }

    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerListener(this, new VoteListener());
        getProxy().getPluginManager().registerCommand(this, new ReloadCommand());
    }

    @Override
    public File dataFolder() {
        return getDataFolder();
    }

    @Override
    public InputStream resource(String name) {
        return getResourceAsStream(name);
    }


    @Override
    public void log(String msg) {
        getLogger().severe(msg);
    }

    @Override
    public void broadcast(String msg) {
        String message = ChatColor.translateAlternateColorCodes('&', msg);
        TextComponent component = new TextComponent(message);

        for (ProxiedPlayer player : getProxy().getPlayers()) {
            player.sendMessage(component);
        }
        getProxy().getConsole().sendMessage(component);
    }

    @Override
    public void runCommand(String cmd) {
        getProxy().getPluginManager().dispatchCommand(getProxy().getConsole(), cmd);
    }

    private class ReloadCommand extends Command {

        public ReloadCommand() {
            super("tuffvotereload");
        }


        @Override
        public void execute(CommandSender sender, String[] args) {
            sender.sendMessage(TextComponent.fromLegacy("config reloaded!"));
            Config.i().reload();
        }
    }
}
