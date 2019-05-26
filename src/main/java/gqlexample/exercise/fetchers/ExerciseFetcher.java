package gqlexample.exercise.fetchers;

import gqlexample.exercise.Exercise;
import gqlexample.exercise.ExerciseType;
import gqlexample.exercise.repository.ExerciseRepository;
import gqlexample.exercise.specification.ExerciseSpecificationCombiner;
import gqlexample.gql.DataFetcherWithWiring;
import gqlexample.gql.WiringTypeName;
import graphql.schema.DataFetchingEnvironment;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

import static gqlexample.exercise.fetchers.ExerciseFieldName.EXERCISES;

@Singleton
public class ExerciseFetcher implements DataFetcherWithWiring<List<Exercise>> {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseSpecificationCombiner exerciseSpecificationCombiner;

    @Inject
    public ExerciseFetcher(
        final ExerciseRepository exerciseRepository,
        final ExerciseSpecificationCombiner exerciseSpecificationCombiner
    ) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseSpecificationCombiner = exerciseSpecificationCombiner;
    }

    @Override
    public List<Exercise> get(final DataFetchingEnvironment env) {
        return exerciseSpecificationCombiner.generate(env)
            .map(exerciseRepository::findBy)
            .orElseGet(exerciseRepository::findAll);
    }

    @Override
    public String getFieldName() {
        return EXERCISES.getName();
    }

    @Override
    public WiringTypeName getWiringType() {
        return WiringTypeName.QUERY;
    }
}
