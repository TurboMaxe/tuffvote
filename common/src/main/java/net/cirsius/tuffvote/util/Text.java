package net.cirsius.tuffvote.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Text {

    @SafeVarargs
    public String format(String message, Tuple<String, Object>... args) {
        String result = message;

        for (Tuple<String, Object> token : args) {
            if (token.getSecond() instanceof String string) {
                result = result.replace(token.getFirst(), string);
            }
        }
        return "";
    }
}
