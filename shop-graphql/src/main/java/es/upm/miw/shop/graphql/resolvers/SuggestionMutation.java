package es.upm.miw.shop.graphql.resolvers;

import es.upm.miw.shop.graphql.data.Suggestion;
import es.upm.miw.shop.graphql.data.SuggestionDao;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SuggestionMutation implements GraphQLMutationResolver {

    private SuggestionDao suggestionDao;

    @Autowired
    public SuggestionMutation(SuggestionDao suggestionDao) {
        this.suggestionDao = suggestionDao;
    }

    public Suggestion createSuggestion(Suggestion suggestion) {
        return this.suggestionDao.save(suggestion);
    }
}