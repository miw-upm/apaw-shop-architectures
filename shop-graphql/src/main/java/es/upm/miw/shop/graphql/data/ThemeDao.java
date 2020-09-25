package es.upm.miw.shop.graphql.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ThemeDao extends MongoRepository<Theme, String> {
}
