package es.upm.miw.shop.adapters.rest;

import es.upm.miw.shop.domain.in_ports.ArticleService;
import es.upm.miw.shop.domain.models.Article;
import es.upm.miw.shop.domain.models.ArticleCreation;
import es.upm.miw.shop.domain.models.ArticlePriceUpdating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(ArticleResource.ARTICLES)
public class ArticleResource {
    static final String ARTICLES = "/articles";
    static final String SEARCH = "/search";

    private ArticleService articleService;

    @Autowired
    public ArticleResource(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public Article create(@RequestBody ArticleCreation articleCreation) {
        return this.articleService.create(articleCreation);
    }

    @PatchMapping
    public void updatePrices(@RequestBody List<ArticlePriceUpdating> articlePriceUpdatingList) {
        this.articleService.updatePrices(articlePriceUpdatingList);
    }

    @GetMapping(SEARCH)
    public Stream<Article> findByProviderAndPriceGreaterThan(@RequestParam String q) {
        String provider = new LexicalAnalyzer().extractWithAssure(q, "provider");
        BigDecimal price = new LexicalAnalyzer().extractWithAssure(q, "price", BigDecimal::new);
        return this.articleService.findByProviderAndPriceGreaterThan(provider, price);
    }

}
