package es.upm.miw.shop.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Document
public class ShoppingCartEntity {
    @Id
    private String id;
    private LocalDateTime creationDate;
    private List<ArticleItemEntity> articleItemEntities;
    private String user;
    private String address;

    public ShoppingCartEntity() {
        //empty from framework
    }

    public ShoppingCartEntity(List<ArticleItemEntity> articleItemEntities, String user, String address) {
        this.id = UUID.randomUUID().toString();
        this.creationDate = LocalDateTime.now();
        this.articleItemEntities = articleItemEntities;
        this.user = user;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public List<ArticleItemEntity> getArticleItemEntities() {
        return articleItemEntities;
    }

    public void setArticleItemEntities(List<ArticleItemEntity> articleItemEntities) {
        this.articleItemEntities = articleItemEntities;
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

    public BigDecimal total() {
        return this.getArticleItemEntities().stream()
                .map(articleItemEntity -> {
                    BigDecimal discount = BigDecimal.ONE.subtract(
                            articleItemEntity.getDiscount().divide(new BigDecimal(100), 4, RoundingMode.HALF_UP)
                    );
                    return articleItemEntity.getArticleEntity().getPrice()
                            .multiply(BigDecimal.valueOf(articleItemEntity.getAmount())
                                    .multiply(discount)
                            );
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((ShoppingCartEntity) obj).id));
    }

    @Override
    public String toString() {
        return "ShoppingCartEntity{" +
                "id='" + id + '\'' +
                ", creationDate=" + creationDate +
                ", articleItemEntities=" + articleItemEntities +
                ", user='" + user + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
