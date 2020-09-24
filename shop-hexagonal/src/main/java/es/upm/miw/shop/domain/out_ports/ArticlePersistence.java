package es.upm.miw.shop.domain.out_ports;

import es.upm.miw.shop.domain.models.Article;
import es.upm.miw.shop.domain.models.ArticleCreation;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Repository
public interface ArticlePersistence {

    Stream<Article> readAll();

    Article create(ArticleCreation articleCreation);

    Article update(Article article);

    Article readByBarcode(Long barcode);

    void assertBarcodeNotExist(Long barcode);

    Stream<Article> findByProviderAndPriceGreaterThan(String provider, BigDecimal price);
}
