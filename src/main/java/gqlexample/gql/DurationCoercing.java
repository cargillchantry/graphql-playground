package gqlexample.gql;

import graphql.GraphQLException;
import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Singleton;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.function.Function;


@Singleton
public class DurationCoercing implements Coercing<Duration, String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DurationCoercing.class);
    @Override
    public String serialize(final Object object) throws CoercingSerializeException {
        if(object instanceof Duration) {
            return ((Duration) object).toString();
        }
        throw new CoercingSerializeException(
            "Unable to serialize duration as it was not a Duration object."
        );
    }

    @Override
    public Duration parseValue(final Object object) throws CoercingParseValueException {
        return parseDuration(String.valueOf(object), CoercingParseValueException::new);
    }

    @Override
    public Duration parseLiteral(final Object object) throws CoercingParseLiteralException {
        if(!(object instanceof StringValue)) {
            throw new CoercingParseLiteralException(
                "Unable to coerce object " + object + " as duration. Expected a StringValue."
            );
        }
        return parseDuration(((StringValue) object).getValue(), CoercingParseLiteralException::new);
    }

    private <T extends GraphQLException> Duration parseDuration(final String toParse, final Function<String, T> toThrow) throws T {
        try {
            return Duration.parse(toParse);
        }
        catch(final DateTimeParseException ex) {
            LOGGER.error("Problem parsing input as duration", ex);
            throw toThrow.apply("Unable to parse " + toParse + " as a Duration.");
        }
    }
}
