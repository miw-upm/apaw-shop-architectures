package es.upm.miw.shop.data;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;

public class ArticleItemEntity {
    @DBRef
    private ArticleEntity articleEntity;
    private Integer amount;
    private BigDecimal discount;

    public ArticleItemEntity() {
        //empty for framework
    }

    public ArticleItemEntity(ArticleEntity articleEntity, Integer amount, BigDecimal discount) {
        this.articleEntity = articleEntity;
        this.amount = amount;
        this.discount = discount;
    }

    public ArticleEntity getArticleEntity() {
        return articleEntity;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "ArticleItemDto{" +
                "articleEntity=" + articleEntity +
                ", amount=" + amount +
                ", discount=" + discount +
                '}';
    }
}
