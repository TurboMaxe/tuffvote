package net.cirsius.tuffvote.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Tuple<L, R> {
    private final L first;
    private final R second;

    public static <L, R> Tuple<L, R> of(final L first, final R second) {
         return new Tuple<>(first, second);
    }
}
