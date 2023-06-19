package org.campusmolndal.user;

import org.campusmolndal.interfaces.Repository;
import org.campusmolndal.todo.TodoDao;

public class User {
    Repository<UserDao> userRepository;
    Repository<TodoDao> todoRepository;
    public User(Repository<UserDao> userRepository, Repository<TodoDao> todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }
    public void create(UserDao userDao) {
        userRepository.create(userDao);
    }
    public UserDao read(String id) {
        UserDao user = userRepository.read(id);
        if(user != null) user.setTodos(todoRepository.search("user",user.getId()));
        return user;
    }

}