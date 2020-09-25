package es.upm.miw.shop.graphql.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
public class Theme {

    @Id
    private String id;

    private String reference;

    private LocalDateTime date;

    @DBRef
    private User user;

    private List<Vote> votes;

    public Theme(String reference, User user) {
        this.reference = reference;
        this.user = user;
        this.date = LocalDateTime.now();
        this.votes = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public List<Vote> getVotes() {
        return votes;
    }

}
