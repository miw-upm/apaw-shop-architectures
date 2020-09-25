package es.upm.miw.shop.rest.dtos;

import es.upm.miw.shop.data.ArticleEntity;
import es.upm.miw.shop.data.TagEntity;

import java.util.List;
import java.util.stream.Collectors;

public class Tag {
    private String id;
    private String description;
    private List<Long> articlesBarcode;
    private Boolean favourite;

    public Tag() {
        //empty for framework
    }

    public Tag(String id, String description, List<Long> articlesBarcode, Boolean favourite) {
        this.id = id;
        this.description = description;
        this.articlesBarcode = articlesBarcode;
        this.favourite = favourite;
    }

    public Tag(TagEntity tagEntity) {
        this.id = tagEntity.getId();
        this.description = tagEntity.getDescription();
        this.articlesBarcode = tagEntity.getArticleEntities().stream()
                .map(ArticleEntity::getBarcode)
                .collect(Collectors.toList());
        this.favourite = tagEntity.getFavourite();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getArticlesBarcode() {
        return articlesBarcode;
    }

    public void setArticlesBarcode(List<Long> articlesBarcode) {
        this.articlesBarcode = articlesBarcode;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", articlesBarcode=" + articlesBarcode +
                ", favourite=" + favourite +
                '}';
    }
}
