package net.cirsius.tuffvote.config;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;
import de.exlll.configlib.NameFormatters;
import de.exlll.configlib.YamlConfigurationProperties;
import de.exlll.configlib.YamlConfigurations;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.cirsius.tuffvote.types.TuffVotePlatform;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Getter
@Configuration
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings("FieldMayBeFinal")
public class Config {
    private static Config instance;
    @Setter private static TuffVotePlatform plugin;

    private static final String CONFIG_HEADER = """
            #########################################
            #                  TuffVote             #
            #########################################
            """;

    private static final YamlConfigurationProperties PROPERTIES = YamlConfigurationProperties.newBuilder()
            .charset(StandardCharsets.UTF_8)
            .setNameFormatter(NameFormatters.LOWER_KEBAB_CASE)
            .header(CONFIG_HEADER).build();

    @Comment("Commands to run after a vote.")
    private List<String> commands = List.of();

    @Comment("The message that should be broadcasted after a player has voted.")
    private String broadcastMessage = "%player% voted!";

    @Comment("A list of service names the vote service should broadcast in the broadcast message.")
    private List<String> services = List.of();


    public void save() {
        YamlConfigurations.save(new File(plugin.dataFolder(), "config.yml").toPath(), Config.class, this);
    }

    public static void reload() {
        instance = YamlConfigurations.load(new File(plugin.dataFolder(), "config.yml").toPath(), Config.class, PROPERTIES);
    }

    public static Config i() {
        if (instance != null) {
            return instance;
        }

        return instance = YamlConfigurations.update(new File(plugin.dataFolder(), "config.yml").toPath(), Config.class, PROPERTIES);
    }
}
