package es.upm.miw.shop.graphql;

import es.upm.miw.shop.data.ShoppingCartEntity;
import es.upm.miw.shop.services.exceptions.LexicalAnalyzer;
import es.upm.miw.shop.services.ShoppingCartService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ShoppingCartQuery implements GraphQLQueryResolver {

    private ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartQuery(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    public List<ShoppingCartEntity> findShoppingCartByPriceGreaterThan(String q) {
        BigDecimal price = new LexicalAnalyzer().extractWithAssure(q, "price", BigDecimal::new);
        return this.shoppingCartService.findByPriceGreaterThan(price)
                .collect(Collectors.toList());
    }

}
