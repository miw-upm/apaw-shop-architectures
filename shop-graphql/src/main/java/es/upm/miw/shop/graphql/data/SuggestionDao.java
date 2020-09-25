package es.upm.miw.shop.graphql.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SuggestionDao extends MongoRepository<Suggestion, String> {
    List<Suggestion> findByNegative(boolean negative);
}
