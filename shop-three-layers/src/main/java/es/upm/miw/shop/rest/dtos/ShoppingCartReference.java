package es.upm.miw.shop.rest.dtos;

import es.upm.miw.shop.data.ShoppingCartEntity;

public class ShoppingCartReference {

    private String id;
    private String user;

    public ShoppingCartReference() {
        //empty for framework
    }

    public ShoppingCartReference(ShoppingCartEntity shoppingCartEntity) {
        this.id = shoppingCartEntity.getId();
        this.user = shoppingCartEntity.getUser();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ShoppingCartReference{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
