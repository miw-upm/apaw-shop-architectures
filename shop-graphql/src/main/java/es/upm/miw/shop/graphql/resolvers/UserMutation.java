package es.upm.miw.shop.graphql.resolvers;

import es.upm.miw.shop.graphql.NotFoundException;
import es.upm.miw.shop.graphql.data.Address;
import es.upm.miw.shop.graphql.data.User;
import es.upm.miw.shop.graphql.data.UserDao;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMutation implements GraphQLMutationResolver {

    private UserDao userDao;

    @Autowired
    public UserMutation(UserDao userDao) {
        this.userDao = userDao;
    }

    public User createUser(String nick, String email, Address address) {
        return this.userDao.save(new User(nick, email, address));
    }

    public User updateUser(String nick, String email, Address address) {
        User userBD = this.userDao.findByNick(nick).orElseThrow(() -> new NotFoundException(nick));
        userBD.setEmail(email);
        userBD.setAddress(address);
        return this.userDao.save(userBD);
    }

    public int deleteUser(String nick) {
        this.userDao.findByNick(nick).ifPresent(user -> this.userDao.delete(user));
        return 0;
    }

}