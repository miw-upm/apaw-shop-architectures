package es.upm.miw.shop.graphql;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    public String hello() {
        return "Hello World!";
    }

}
