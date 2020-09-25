package es.upm.miw.shop.graphql.resolvers;

import es.upm.miw.shop.graphql.NotFoundException;
import es.upm.miw.shop.graphql.data.User;
import es.upm.miw.shop.graphql.data.UserDao;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserQuery implements GraphQLQueryResolver {

    private UserDao userDao;

    @Autowired
    public UserQuery(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getUsers() {
        return this.userDao.findAll();
    }

    public User getUser(String nick) {
        return this.userDao.findByNick(nick).orElseThrow(() -> new NotFoundException("nick: " + nick));
    }

}
