package gqlexample.gql;

import graphql.schema.DataFetcher;

public interface DataFetcherWithWiring<T> extends DataFetcher<T> {
    String getFieldName();
    String getWiringType();
}
