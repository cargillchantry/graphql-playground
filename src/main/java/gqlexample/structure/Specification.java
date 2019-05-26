package gqlexample.structure;

public interface Specification<T> {
    boolean satisfiedBy(final T element);
    default Specification<T> and(final Specification<? super T> specification) {
        return element -> satisfiedBy(element) && specification.satisfiedBy(element);
    }
    default Specification<T> or(final Specification<? super T> specification) {
        return element -> satisfiedBy(element) || specification.satisfiedBy(element);
    }
    default Specification<T> andNot(final Specification<? super T> specification) {
        return element -> satisfiedBy(element) && !specification.satisfiedBy(element);
    }
    default Specification<T> orNot(final Specification<? super T> specification) {
        return element -> satisfiedBy(element) || !specification.satisfiedBy(element);
    }
}
