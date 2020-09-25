package es.upm.miw.shop.graphql.exceptions;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BadRequestException extends RuntimeException implements GraphQLError {
    private static final String DESCRIPTION = "Bad Request Exception";
    public BadRequestException(String detail) {
        super(DESCRIPTION + detail);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.ValidationError;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return Collections.singletonMap(DESCRIPTION, this.getMessage());
    }
}

