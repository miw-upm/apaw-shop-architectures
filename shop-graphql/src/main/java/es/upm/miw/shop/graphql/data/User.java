package es.upm.miw.shop.graphql.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

    @Id
    private String id;

    private String nick;

    private String email;

    private Address address;

    public User(String nick, String email, Address address) {
        this.nick = nick;
        this.email = email;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", nick='" + nick + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}';
    }
}
