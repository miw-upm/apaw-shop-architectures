package es.upm.miw.shop.graphql.resolvers;

import es.upm.miw.shop.graphql.NotFoundException;
import es.upm.miw.shop.graphql.data.Theme;
import es.upm.miw.shop.graphql.data.ThemeDao;
import es.upm.miw.shop.graphql.data.User;
import es.upm.miw.shop.graphql.data.UserDao;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ThemeMutation implements GraphQLMutationResolver {

    private UserDao userDao;

    private ThemeDao themeDao;

    @Autowired
    public ThemeMutation(UserDao userDao, ThemeDao themeDao) {
        this.userDao = userDao;
        this.themeDao = themeDao;
    }

    public Theme createTheme(ThemeCreation themeCreation) {
        User userBD = this.userDao.findById(themeCreation.getUserId())
                .orElseThrow(() -> new NotFoundException(themeCreation.getUserId()));
        return this.themeDao.save(new Theme(themeCreation.getReference(), userBD));
    }

}