package gqlexample.exercise.repository;

import gqlexample.exercise.Exercise;
import gqlexample.structure.Specification;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<Exercise> findBy(final Specification<Exercise> specification) {
        return exercises.values().stream()
            .filter(specification::satisfiedBy)
            .collect(Collectors.toUnmodifiableList());
    }

    public Exercise save(final Exercise exercise) {
        exercises.put(exercise.getId(), exercise);
        return exercise;
    }
}
