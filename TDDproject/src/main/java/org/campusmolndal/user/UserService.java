package org.campusmolndal.user;

import org.campusmolndal.interfaces.UserDao;

import java.util.List;

public class UserService {
    private UserDao userDao;
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
    public User getUser(String id) {
        return userDao.read(id);
    }
    public User create(User user) {
        return userDao.create(user);
    }
    public User changeName(User user, String name) {
        user.setName(name);
        return update(user);
    }
    public User deleteUser(User user) {
        return userDao.delete(user.getId());
    }
    public List<User> listUsers() {
        return userDao.list();
    }
    private User update(User user) {
        return userDao.update(user.getId(), user);
    }
}
