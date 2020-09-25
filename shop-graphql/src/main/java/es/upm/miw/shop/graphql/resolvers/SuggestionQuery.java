package es.upm.miw.shop.graphql.resolvers;

import es.upm.miw.shop.graphql.data.Suggestion;
import es.upm.miw.shop.graphql.data.SuggestionDao;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class SuggestionQuery implements GraphQLQueryResolver {

    private SuggestionDao suggestionDao;

    @Autowired
    public SuggestionQuery(SuggestionDao suggestionDao) {
        this.suggestionDao = suggestionDao;
    }

    public List<Suggestion> suggestions(Optional<Boolean> negative) {
        if (negative.isPresent()) {
            return this.suggestionDao.findByNegative(negative.get());
        } else {
            return this.suggestionDao.findAll();
        }
    }

}
