package gqlexample.exercise.fetchers;

public enum WiringTypeName {
    MUTATION("ExerciseMutation"), QUERY("ExerciseQuery");

    private final String type;

    WiringTypeName(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
