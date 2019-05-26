package gqlexample.structure;

public class UnitSpecification<T> implements Specification<T> {
    @Override
    public boolean satisfiedBy(final T element) {
        return true;
    }

    @Override
    public Specification<T> and(final Specification<T> specification) {
        return specification;
    }

    @Override
    public Specification<T> or(final Specification<T> specification) {
        return specification;
    }

    @Override
    public Specification<T> andNot(final Specification<T> specification) {
        return exercise -> !specification.satisfiedBy(exercise);
    }

    @Override
    public Specification<T> orNot(final Specification<T> specification) {
        return exercise -> !specification.satisfiedBy(exercise);
    }
}
