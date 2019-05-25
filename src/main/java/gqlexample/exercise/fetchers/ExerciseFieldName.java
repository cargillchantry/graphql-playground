package gqlexample.exercise.fetchers;

public enum ExerciseFieldName {
    EXERCISES("exercises"), CREATE_EXERCISE("createExercise");

    private final String name;

    ExerciseFieldName(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
