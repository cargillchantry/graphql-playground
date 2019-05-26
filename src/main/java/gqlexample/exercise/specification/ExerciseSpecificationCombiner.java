package gqlexample.exercise.specification;

import gqlexample.exercise.Exercise;
import gqlexample.structure.Specification;
import gqlexample.structure.UnitSpecification;
import graphql.schema.DataFetchingEnvironment;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;
import java.util.Optional;

@Singleton
public class ExerciseSpecificationCombiner {
    private static final Specification<Exercise> ROOT_SPECIFICATION = new UnitSpecification<>();

    private final Collection<ExerciseSpecificationGenerator> generators;

    @Inject
    public ExerciseSpecificationCombiner(final Collection<ExerciseSpecificationGenerator> generators) {
        this.generators = generators;
    }

    public Optional<Specification<Exercise>> generate(final DataFetchingEnvironment dataFetchingEnvironment) {
        Specification<Exercise> previous = ROOT_SPECIFICATION;
        for(final ExerciseSpecificationGenerator generator: generators) {
            previous = generator.generate(previous, dataFetchingEnvironment);
        }
        return previous == ROOT_SPECIFICATION ? Optional.empty() : Optional.of(previous);
    }
}
