package org.campusmolndal.sqlite;

import org.campusmolndal.interfaces.UserDao;
import org.campusmolndal.user.User;

import java.util.List;

public class SQLiteUserDao implements UserDao {
    private final SQLiteHandler sqLiteHandler;
    public SQLiteUserDao(SQLiteHandler sqLiteHandler) {
        this.sqLiteHandler = sqLiteHandler;
    }

    @Override
    public User create(User user) {
        return sqLiteHandler.insert(user);
    }

    @Override
    public User read(String id) {
        return sqLiteHandler.getUser(id);
    }

    @Override
    public User update(User user) {
        return sqLiteHandler.update(user);
    }

    @Override
    public User delete(User user) {
        return sqLiteHandler.delete(user);
    }

    @Override
    public List<User> list() {
        return sqLiteHandler.listUsers();
    }
}
