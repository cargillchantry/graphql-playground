package gqlexample.exercise.specification;

import gqlexample.exercise.Exercise;
import gqlexample.exercise.ExerciseType;
import gqlexample.structure.Specification;

class ExerciseTypeSpecification implements Specification<Exercise> {

    private final String value;

    ExerciseTypeSpecification(final String value) {
        this.value = value;
    }

    @Override
    public boolean satisfiedBy(final Exercise exercise) {
        return exercise.getExerciseType() == ExerciseType.valueOf(value);
    }
}
