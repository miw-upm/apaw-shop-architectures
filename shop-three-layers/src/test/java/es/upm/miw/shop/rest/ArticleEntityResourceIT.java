package es.upm.miw.shop.rest;

import es.upm.miw.shop.rest.dtos.Article;
import es.upm.miw.shop.services.ArticlePriceUpdating;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
class ArticleEntityResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Article article =
                new Article(666004L, "art rest", new BigDecimal("3.00"), null);
        this.webTestClient
                .post()
                .uri(ArticleResource.ARTICLES)
                .body(BodyInserters.fromValue(article))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Article.class)
                .value(Assertions::assertNotNull)
                .value(articleData -> assertNotNull(articleData.getId()));
    }

    @Test
    void testCreateConflict() {
        Article article =
                new Article(84001L, "repeated", new BigDecimal("3.00"), null);
        this.webTestClient
                .post()
                .uri(ArticleResource.ARTICLES)
                .body(BodyInserters.fromValue(article))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdatePricesNotFound() {
        List<ArticlePriceUpdating> articlePriceUpdatingList = Arrays.asList(
                new ArticlePriceUpdating(0L, BigDecimal.ONE)
        );
        this.webTestClient
                .patch()
                .uri(ArticleResource.ARTICLES)
                .body(BodyInserters.fromValue(articlePriceUpdatingList))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testSearchByProviderAndPriceGreaterThan() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(ArticleResource.ARTICLES + ArticleResource.SEARCH)
                                .queryParam("q", "provider:prov 1;price:1.02")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Article.class)
                .value(articles -> assertTrue(articles.size() > 0))
                .value(articles -> assertEquals("prov 1", articles.get(0).getProvider()))
                .value(articles -> assertTrue(new BigDecimal("1.02").compareTo(articles.get(0).getPrice()) < 0));
    }

    @Test
    void testSearchByProviderAndPriceGreaterThanBadRequest() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(ArticleResource.ARTICLES + ArticleResource.SEARCH)
                                .queryParam("q", "kk:prov 1;price:1.02")
                                .build())
                .exchange()
                .expectStatus().isBadRequest();
    }
}
