package es.upm.miw.shop.services;

import es.upm.miw.shop.data.ArticleEntity;
import es.upm.miw.shop.data.ArticleRepository;
import es.upm.miw.shop.services.exceptions.ConflictException;
import es.upm.miw.shop.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Stream<ArticleEntity> readAll() {
        return this.articleRepository
                .findAll().stream();
    }

    public void assertBarcodeNotExist(Long barcode) {
        this.articleRepository
                .findByBarcode(barcode)
                .ifPresent(article -> {
                    throw new ConflictException("Barcode exist: " + barcode);
                });
    }


    public ArticleEntity create(ArticleEntity articleEntity) {
        this.assertBarcodeNotExist(articleEntity.getBarcode());
        return this.articleRepository.save(articleEntity);
    }

    public void updatePrices(List<ArticlePriceUpdating> articlePriceUpdatingList) {
        this.articleRepository.saveAll(
                articlePriceUpdatingList.stream()
                        .map(articleNewPrice -> {
                            ArticleEntity articleEntity = this.articleRepository.findByBarcode(articleNewPrice.getBarcode())
                                    .orElseThrow(() -> new NotFoundException("Article barcode: " + articleNewPrice.getBarcode()));
                            articleEntity.setPrice(articleNewPrice.getPrice());
                            return articleEntity;
                        })
                        .collect(Collectors.toList())
        );
    }

    public Stream<ArticleEntity> findByProviderAndPriceGreaterThan(String provider, BigDecimal price) {
        return this.articleRepository.findAll().stream()
                .filter(article -> provider.equals(article.getProvider()))
                .filter(article -> price.compareTo(article.getPrice()) < 0);
    }

}
