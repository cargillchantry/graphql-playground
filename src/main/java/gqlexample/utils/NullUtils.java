package gqlexample.utils;

import java.util.function.Function;

public class NullUtils {
    private NullUtils(){}

    public static <K, T> T nullSafeMap(final K element, final Function<? super K, ? extends T> mapper) {
        return element == null ? null : mapper.apply(element);
    }
}
