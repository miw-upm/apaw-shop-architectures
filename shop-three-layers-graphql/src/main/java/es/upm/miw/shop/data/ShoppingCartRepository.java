package es.upm.miw.shop.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCartEntity, String> {
}