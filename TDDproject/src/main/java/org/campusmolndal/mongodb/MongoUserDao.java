package org.campusmolndal.mongodb;

import org.campusmolndal.interfaces.UserDao;
import org.campusmolndal.user.User;


import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class MongoUserDao implements UserDao {
    private final MongoFacade mongoFacade = new MongoFacade("users");
    @Override
    public User create(User user) {
        if(user == null) return null;
        return User.fromDocument(mongoFacade.insert(user.toDocument()));
    }

    @Override
    public User read(String id) {
        if(id == null) return null;
        try {
            return User.fromDocument(mongoFacade.get(id));
        } catch (Exception e) {
            System.err.println("Error in MongoUserDao.read: " + e.getMessage());
            return null;
        }
    }

    @Override
    public User update(User user) {
        if(user == null) return null;
        if(user.getId() == null) return null;
        try {
            return User.fromDocument(mongoFacade.update(user.toDocument()));
        } catch (Exception e) {
            System.err.println("Error in MongoUserDao.update: " + e.getMessage());
            return null;
        }
    }

    @Override
    public User delete(User user) {
        try {
            return User.fromDocument(mongoFacade.delete(user.toDocument()));
        } catch (Exception e) {
            System.err.println("Error in MongoUserDao.delete: " + e.getMessage());
            return null;
        }
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