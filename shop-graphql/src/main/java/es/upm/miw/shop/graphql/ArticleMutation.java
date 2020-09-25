package es.upm.miw.shop.graphql;

import es.upm.miw.shop.data.ArticleEntity;
import es.upm.miw.shop.services.ArticlePriceUpdating;
import es.upm.miw.shop.services.ArticleService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ArticleMutation implements GraphQLMutationResolver {

    private ArticleService articleService;

    @Autowired
    public ArticleMutation(ArticleService articleService) {
        this.articleService = articleService;
    }

    public ArticleEntity createArticle(ArticleEntity articleEntity ) {
        return this.articleService.create(articleEntity);
    }

    public void updatePricesArticles(List<ArticlePriceUpdating> articlePriceUpdatingList) {
        this.articleService.updatePrices(articlePriceUpdatingList);
    }
}
