package gqlexample.exercise.fetchers;

import gqlexample.exercise.Exercise;
import gqlexample.exercise.ExerciseType;
import gqlexample.exercise.repository.ExerciseRepository;
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

    @Inject
    public ExerciseFetcher(final ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<Exercise> get(final DataFetchingEnvironment env) {
        if(env.containsArgument("exerciseType")) {
            return exerciseRepository.findBy(
                exercise -> exercise.getExerciseType() == ExerciseType.valueOf(env.getArgument("exerciseType"))
            );
        }
        return exerciseRepository.findAll();
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
