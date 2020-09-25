package es.upm.miw.shop.services;

import es.upm.miw.shop.data.*;
import es.upm.miw.shop.graphql.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TagService {

    private TagRepository tagRepository;
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public TagService(TagRepository tagRepository, ShoppingCartRepository shoppingCartRepository) {
        this.tagRepository = tagRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public Stream<TagEntity> readAll() {
        return this.tagRepository.findAll().stream();
    }

    public TagEntity readById(String id) {
        return this.tagRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(" Tag id: " + id));
    }

    public void deleteById(String id) {
        this.tagRepository.deleteById(id);
    }

    public Stream<TagEntity> findByArticlesInShoppingCarts() {
        List<ArticleEntity> articleEntityList = this.shoppingCartRepository.findAll().stream()
                .flatMap(shoppingCartEntity -> shoppingCartEntity.getArticleItemEntities().stream())
                .map(ArticleItemEntity::getArticleEntity)
                .collect(Collectors.toList());
        return this.tagRepository.findAll().stream()
                .filter(tagEntity -> tagEntity.getArticleEntities().stream()
                        .anyMatch(articleEntityList::contains));
    }
}
