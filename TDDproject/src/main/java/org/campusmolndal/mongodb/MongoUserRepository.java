package org.campusmolndal.mongodb;

import org.campusmolndal.user.UserDao;

import java.util.List;

public class MongoUserRepository extends MongoRepository<UserDao> {
    public MongoUserRepository() {
        super("users", UserDao.class);
    }
}
