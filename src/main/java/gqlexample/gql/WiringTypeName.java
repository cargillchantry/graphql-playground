package gqlexample.gql;

public enum WiringTypeName {
    MUTATION("Mutation"), QUERY("Query");

    private final String type;

    WiringTypeName(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
