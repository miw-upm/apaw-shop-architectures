package es.upm.miw.shop.graphql.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserDao extends MongoRepository<User, String> {
    Optional<User> findByNick(String nick);
}
