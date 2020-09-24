package es.upm.miw.shop.adapters.mongodb;

import es.upm.miw.shop.domain.exceptions.NotFoundException;
import es.upm.miw.shop.domain.models.ShoppingCart;
import es.upm.miw.shop.domain.out_ports.ShoppingCartPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("shoppingCartPersistence")
public class ShoppingCartPersistenceMongodb implements ShoppingCartPersistence {

    private ShoppingCartRepository shoppingCartRepository;

    private ArticleRepository articleRepository;

    @Autowired
    public ShoppingCartPersistenceMongodb(ShoppingCartRepository shoppingCartRepository, ArticleRepository articleRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.articleRepository = articleRepository;
    }


    @Override
    public Stream<ShoppingCart> readAll() {
        return this.shoppingCartRepository.findAll().stream()
                .map(ShoppingCartEntity::toShoppingCart);
    }

    @Override
    public ShoppingCart readById(String id) {
        return this.shoppingCartRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("ShoppingCart id:" + id))
                .toShoppingCart();
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        ShoppingCartEntity shoppingCartEntity = this.shoppingCartRepository
                .findById(shoppingCart.getId())
                .orElseThrow(() -> new NotFoundException("ShoppingCart id:" + shoppingCart.getId()));
        List<ArticleItemEntity> articleItemEntities = shoppingCart.getArticleItems().stream()
                .map(articleItem -> new ArticleItemEntity(
                        this.articleRepository
                                .findByBarcode(articleItem.getBarcode())
                                .orElseThrow(() -> new NotFoundException("Article barcode: " + articleItem.getBarcode())),
                        articleItem.getAmount(),
                        articleItem.getDiscount())

                ).collect(Collectors.toList());
        shoppingCartEntity.setArticleItemEntities(articleItemEntities);
        return this.shoppingCartRepository.save(shoppingCartEntity).toShoppingCart();
    }

}
