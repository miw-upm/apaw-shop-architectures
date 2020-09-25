package es.upm.miw.shop.graphql.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DatabaseSeeder {

    private UserDao userDao;

    private SuggestionDao suggestionDao;

    private ThemeDao themeDao;

    private List<User> users;

    @Autowired
    public DatabaseSeeder(UserDao userDao, SuggestionDao suggestionDao, ThemeDao themeDao) {
        this.userDao = userDao;
        this.suggestionDao = suggestionDao;
        this.themeDao = themeDao;
        this.seedUsers();
        this.seedSuggestions();
        this.seedThemes();
    }

    public void seedUsers() {
        this.users = Stream.iterate(0, i -> i + 1).limit(5)
                .map(i -> new User("nick" + i, "email" + i,
                        new Address("country" + i, "city" + i, "street" + i)))
                .collect(Collectors.toList());
        this.userDao.saveAll(this.users);
    }

    public void seedSuggestions() {
        this.suggestionDao.saveAll(Arrays.asList(
                new Suggestion(true, "No me gusta 1"),
                new Suggestion(true, "No me gusta 2"),
                new Suggestion(false, "Me gusta!!! 1"),
                new Suggestion(false, "Me gusta!!! 2")
        ));
    }

    public void seedThemes() {
        this.themeDao.saveAll(Arrays.asList(
                new Theme("reference 1 de 0", this.users.get(0)),
                new Theme("reference 2 de 0", this.users.get(0)),
                new Theme("reference 3 de 1", this.users.get(1))
        ));

    }
}
