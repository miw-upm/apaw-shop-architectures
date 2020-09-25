package es.upm.miw.shop.graphql;

import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class MutationResolver implements GraphQLMutationResolver {

    public String createPerson(String name, Integer age) {
        return name + ":" + age;
    }

    public Person createPerson2(Person person) {
        return person;
    }

}