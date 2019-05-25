package gqlexample.utils;

import java.util.function.Function;

public class NullUtils {
    private NullUtils(){}

    public static <K, T> T nullSafeMap(final K element, final Function<K, T> mapper) {
        return element == null ? null : mapper.apply(element);
    }
}
