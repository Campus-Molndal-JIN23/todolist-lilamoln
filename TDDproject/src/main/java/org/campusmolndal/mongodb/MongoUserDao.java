package org.campusmolndal.mongodb;

import org.campusmolndal.interfaces.UserDao;
import org.campusmolndal.user.User;

public class MongoUserDao extends MongoDao<User> implements UserDao {
    public MongoUserDao() {
        super("users", User.class);
    }
}
