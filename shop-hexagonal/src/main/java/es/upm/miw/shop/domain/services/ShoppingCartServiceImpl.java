package es.upm.miw.shop.domain.services;

import es.upm.miw.shop.domain.in_ports.ShoppingCartService;
import es.upm.miw.shop.domain.models.ArticleItem;
import es.upm.miw.shop.domain.models.ShoppingCart;
import es.upm.miw.shop.domain.models.ShoppingCartReference;
import es.upm.miw.shop.domain.out_ports.ArticlePersistence;
import es.upm.miw.shop.domain.out_ports.ShoppingCartPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Stream;

@Service("shoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ShoppingCartPersistence shoppingCartPersistence;
    private ArticlePersistence articlePersistence;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartPersistence shoppingCartPersistence, ArticlePersistence articlePersistence) {
        this.shoppingCartPersistence = shoppingCartPersistence;
        this.articlePersistence = articlePersistence;
    }

    @Override
    public ShoppingCart updateArticleItems(String id, List<ArticleItem> articleItemList) {
        ShoppingCart shoppingCart = this.shoppingCartPersistence.readById(id);
        shoppingCart.setArticleItems(articleItemList);
        return this.shoppingCartPersistence.update(shoppingCart);
    }

    private BigDecimal total(ShoppingCart shoppingCart) {
        return shoppingCart.getArticleItems().stream()
                .map(articleItem -> {
                    BigDecimal discount = BigDecimal.ONE.subtract(
                            articleItem.getDiscount().divide(new BigDecimal(100), 4, RoundingMode.HALF_UP)
                    );
                    BigDecimal articlePrice = this.articlePersistence.readByBarcode(articleItem.getBarcode()).getPrice();
                    return articlePrice.multiply(BigDecimal.valueOf(articleItem.getAmount())
                            .multiply(discount)
                    );
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    @Override
    public Stream<ShoppingCartReference> findByPriceGreaterThan(BigDecimal price) {
        return this.shoppingCartPersistence.readAll()
                .filter(shoppingCart -> price.compareTo(this.total(shoppingCart)) < 0)
                .map(ShoppingCartReference::new);
    }
}
