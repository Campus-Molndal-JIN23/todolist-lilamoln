package org.campusmolndal.newmongo;

import org.campusmolndal.interfaces.UserDao;
import org.campusmolndal.user.User;


import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class NewMongoUserDao implements UserDao {
    private final MongoFacade mongoFacade = new MongoFacade("users");
    @Override
    public User create(User user) {
        return User.fromDocument(mongoFacade.insert(user.toDocument()));
    }

    @Override
    public User read(String id) {
        return User.fromDocument(mongoFacade.get(id));
    }

    @Override
    public User update(User user) {
        return User.fromDocument(mongoFacade.update(user.toDocument()));

    }

    @Override
    public User delete(User user) {
        return User.fromDocument(mongoFacade.delete(user.toDocument()));
    }

    @Override
    public List<User> list() {
        List<Document> result = mongoFacade.list();
        List<User> users = new ArrayList<>();
        for (Document document : result) {
            users.add(User.fromDocument(document));
        }
        return users;
    }
}
