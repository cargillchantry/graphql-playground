package gqlexample.structure;

import gqlexample.exercise.Exercise;
import graphql.schema.DataFetchingEnvironment;

public interface SpecificationGenerator<T> {
    Specification<T> generate(Specification<Exercise> previous, DataFetchingEnvironment dataFetchingEnvironment);
}
