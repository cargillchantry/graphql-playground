package gqlexample.exercise.specification;

import gqlexample.exercise.Exercise;
import gqlexample.exercise.ExerciseType;
import gqlexample.structure.Specification;
import graphql.schema.DataFetchingEnvironment;
import javax.inject.Singleton;

@Singleton
class ExerciseTypeSpecificationGenerator implements ExerciseSpecificationGenerator {
    @Override
    public Specification<Exercise> generate(
        final Specification<Exercise> previous,
        final DataFetchingEnvironment dataFetchingEnvironment
    ) {
        final String exerciseType = dataFetchingEnvironment.getArgument("exerciseType");
        if(exerciseType == null) {
            return previous;
        }
        return previous.and(exercise -> exercise.getExerciseType() == ExerciseType.valueOf(exerciseType));
    }
}
