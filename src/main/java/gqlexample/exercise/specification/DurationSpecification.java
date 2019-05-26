package gqlexample.exercise.specification;

import gqlexample.exercise.Exercise;
import gqlexample.structure.Specification;

import java.time.Duration;

public class DurationSpecification implements Specification<Exercise> {
    private final String duration;

    DurationSpecification(final String duration) {
        this.duration = duration;
    }

    @Override
    public boolean satisfiedBy(final Exercise element) {
        return element.getDuration().equals(Duration.parse(duration));
    }
}
