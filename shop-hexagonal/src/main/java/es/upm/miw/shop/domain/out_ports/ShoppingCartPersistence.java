package es.upm.miw.shop.domain.out_ports;

import es.upm.miw.shop.domain.models.ShoppingCart;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ShoppingCartPersistence {

    Stream<ShoppingCart> readAll();

    ShoppingCart readById(String id);

    ShoppingCart update(ShoppingCart shoppingCart);
}
