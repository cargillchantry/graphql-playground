package gqlexample.exercise;

import java.time.Duration;
import java.util.UUID;

import static gqlexample.exercise.ExerciseType.RUN;

public class Exercise {
    private final String id;
    private final ExerciseType exerciseType;
    private final Duration duration;

    private Exercise(final ExerciseBuilder exerciseBuilder) {
        this.exerciseType = exerciseBuilder.exerciseType;
        this.duration = exerciseBuilder.duration;
        this.id = exerciseBuilder.previous != null ? exerciseBuilder.previous.id : UUID.randomUUID().toString();
    }

    public static ExerciseBuilder builder() {
        return new ExerciseBuilder(null);
    }

    public ExerciseBuilder mutating() {
        return new ExerciseBuilder(this);
    }

    public String getId() { return id; }

    public ExerciseType getExerciseType() {
        return exerciseType;
    }

    public Duration getDuration() {
        return duration;
    }

    public static final class ExerciseBuilder {
        private final Exercise previous;
        private ExerciseType exerciseType;
        private Duration duration;

        private ExerciseBuilder(final Exercise exercise) {
            this.previous = exercise;
        }

        public ExerciseBuilder setExerciseType(final ExerciseType exerciseType) {
            this.exerciseType = exerciseType;
            return this;
        }

        public ExerciseBuilder setDuration(final Duration duration) {
            this.duration = duration;
            return this;
        }

        public Exercise build() {
            if(exerciseType == null) {
                exerciseType = previous == null ? RUN : previous.exerciseType;
            }
            if(duration == null) {
                duration = previous == null ? Duration.ZERO : previous.duration;
            }
            return new Exercise(this);
        }
    }
}
