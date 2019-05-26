package gqlexample.config;

import gqlexample.gql.DataFetcherWithWiring;
import gqlexample.gql.DurationCoercing;
import graphql.GraphQL;
import graphql.schema.GraphQLScalarType;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.core.io.ResourceResolver;

import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;

@Factory
public class GraphQLFactory {

    @Singleton
    @Bean
    public GraphQL graphQL(
        final ResourceResolver resourceResolver,
        final Collection<DataFetcherWithWiring<?>> dataFetchers,
        final DurationCoercing durationCoercing
        ) {
        final SchemaParser schemaParser = new SchemaParser();
        final SchemaGenerator schemaGenerator = new SchemaGenerator();

        // Parse the schema.
        final TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();
        typeRegistry.merge(schemaParser.parse(new BufferedReader(new InputStreamReader(
            resourceResolver.getResourceAsStream("classpath:schema.graphqls").get()))));

        // Create the runtime wiring.
        final RuntimeWiring.Builder builder = RuntimeWiring.newRuntimeWiring().scalar(GraphQLScalarType.newScalar()
            .coercing(durationCoercing)
            .description("Parses Duration strings from the ISO-8601.")
            .name("Duration")
            .build()
        );
        for(final DataFetcherWithWiring<?> fetcher: dataFetchers) {
            builder.type(
                fetcher.getWiringType().getType(),
                typeWiring -> typeWiring.dataFetcher(fetcher.getFieldName(), fetcher)
            );
        }

        // Create the executable schema.builder.build()
        final GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, builder.build());

        // Return the GraphQL bean.
        return GraphQL.newGraphQL(graphQLSchema).build();
    }
}
