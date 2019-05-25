package gqlexample.storage;

public interface Specification<T> {
    boolean satisfiedBy(final T element);
    default Specification<T> and(final Specification<T> specification) {
        return element -> satisfiedBy(element) && specification.satisfiedBy(element);
    }
    default Specification<T> or(final Specification<T> specification) {
        return element -> satisfiedBy(element) || specification.satisfiedBy(element);
    }
    default Specification<T> andNot(final Specification<T> specification) {
        return element -> satisfiedBy(element) && !specification.satisfiedBy(element);
    }
    default Specification<T> orNot(final Specification<T> specification) {
        return element -> satisfiedBy(element) || !specification.satisfiedBy(element);
    }
}
