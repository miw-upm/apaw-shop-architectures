package es.upm.miw.shop.graphql.resolvers;

public class ThemeCreation {

    private String reference;

    private String userId;

    public ThemeCreation() {
        // Empty for framework
    }

    public ThemeCreation(String reference, String userId) {
        this.reference = reference;
        this.userId = userId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ThemeCreationDto{" +
                "reference='" + reference + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

}
