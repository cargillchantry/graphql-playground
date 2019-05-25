package gqlexample.exercise.repository;

import gqlexample.exercise.Exercise;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class ExerciseRepository {

    private final Map<String, Exercise> exercises;

    ExerciseRepository() {
        exercises = new HashMap<>();
        save(Exercise.builder().build());
    }

    public List<Exercise> findAll() {
        return List.copyOf(exercises.values());
    }

    public Exercise save(final Exercise exercise) {
        exercises.put(exercise.getId(), exercise);
        return exercise;
    }
}
