package es.upm.miw.shop.rest.dtos;

import es.upm.miw.shop.data.ShoppingCartEntity;
import es.upm.miw.shop.services.ArticleItem;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCart {
    private String id;
    private LocalDateTime creationDate;
    private List<ArticleItem> articleItems;
    private String user;
    private String address;

    public ShoppingCart() {
        //empty from framework
    }

    public ShoppingCart(ShoppingCartEntity shoppingCartEntity) {
        BeanUtils.copyProperties(shoppingCartEntity, this, "articleItemEntities");
        this.setArticleItems(
                shoppingCartEntity.getArticleItemEntities().stream()
                        .map(ArticleItem::new)
                        .collect(Collectors.toList())
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public List<ArticleItem> getArticleItems() {
        return articleItems;
    }

    public void setArticleItems(List<ArticleItem> articleItems) {
        this.articleItems = articleItems;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((ShoppingCart) obj).id));
    }

    @Override
    public String toString() {
        return "ShoppingCartEntity{" +
                "id='" + id + '\'' +
                ", creationDate=" + creationDate +
                ", articleItems=" + articleItems +
                ", user='" + user + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
