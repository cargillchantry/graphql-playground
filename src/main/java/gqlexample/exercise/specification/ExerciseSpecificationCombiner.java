package gqlexample.exercise.specification;

import gqlexample.exercise.Exercise;
import gqlexample.structure.Specification;
import graphql.schema.DataFetchingEnvironment;
import javax.inject.Singleton;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;


@Singleton
public class ExerciseSpecificationCombiner {

    public Optional<Specification<Exercise>> generate(final DataFetchingEnvironment dataFetchingEnvironment) {
        return Stream.of(
            generateSpecification(dataFetchingEnvironment.getArgument("duration"), DurationSpecification::new),
            generateSpecification(dataFetchingEnvironment.getArgument("exerciseType"), ExerciseTypeSpecification::new)
        )
        .filter(Optional::isPresent)
        .map(Optional::get)
        .reduce(Specification::and);
    }

    private static <T> Optional<Specification<Exercise>> generateSpecification(final T arg, final Function<T, Specification<Exercise>> mapper) {
        return Optional.ofNullable(arg).map(mapper);
    }
}
