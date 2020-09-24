package es.upm.miw.shop.domain.in_ports;

import es.upm.miw.shop.domain.models.Article;
import es.upm.miw.shop.domain.models.ArticleCreation;
import es.upm.miw.shop.domain.models.ArticlePriceUpdating;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

public interface ArticleService {

    Article create(ArticleCreation articleCreation);

    void updatePrices(List<ArticlePriceUpdating> articlePriceUpdatingList);

    Stream<Article> findByProviderAndPriceGreaterThan(String provider, BigDecimal price);
}
