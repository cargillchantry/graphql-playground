package gqlexample.exercise.fetchers;

import gqlexample.exercise.Exercise;
import gqlexample.exercise.ExerciseType;
import gqlexample.exercise.repository.ExerciseRepository;
import gqlexample.gql.DataFetcherWithWiring;
import gqlexample.gql.WiringTypeName;
import graphql.schema.DataFetchingEnvironment;

import javax.inject.Inject;
import javax.inject.Singleton;

import java.time.Duration;

import static gqlexample.exercise.fetchers.ExerciseFieldName.CREATE_EXERCISE;
import static gqlexample.utils.NullUtils.nullSafeMap;

@Singleton
public class ExerciseCreateFetcher implements DataFetcherWithWiring<Exercise> {

    private final ExerciseRepository exerciseRepository;

    @Inject
    public ExerciseCreateFetcher(final ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public String getFieldName() {
        return CREATE_EXERCISE.getName();
    }

    @Override
    public WiringTypeName getWiringType() {
        return WiringTypeName.MUTATION;
    }

    @Override
    public Exercise get(final DataFetchingEnvironment dataFetchingEnvironment) {
        return exerciseRepository.save(
            Exercise.builder()
                .setExerciseType(nullSafeMap(
                    dataFetchingEnvironment.<String>getArgument("exerciseType"),
                    ExerciseType::valueOf
                ))
                .setDuration(dataFetchingEnvironment.getArgument("duration"))
                .build()
        );
    }
}
