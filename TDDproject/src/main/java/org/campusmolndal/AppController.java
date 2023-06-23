package org.campusmolndal;

import org.campusmolndal.interfaces.TodoDao;
import org.campusmolndal.interfaces.UserDao;
import org.campusmolndal.todo.Todo;
import org.campusmolndal.todo.TodoService;
import org.campusmolndal.user.User;
import org.campusmolndal.user.UserService;

import java.util.ArrayList;
import java.util.List;

public class AppController {
    private final UserService userService;
    private final TodoService todoService;
    public AppController(UserDao userDao, TodoDao todoDao) {
        this.userService = new UserService(userDao);
        this.todoService = new TodoService(todoDao);
    }
    public User getUser(String id) {
        User user = userService.getUser(id);
        if(user != null) {
            user.setTodos(todoService.getByUserId(id));
        }
        return user;
    }
    public User changeName(User user, String name) {
        return userService.changeName(user, name);
    }
    public User delete(User user) {
        return userService.deleteUser(user);
    }
    public User create(User user) {
        return userService.create(user);
    }
    public Todo delete(Todo todo) {
        return todoService.delete(todo);
    }
    public Todo create(Todo todo) {
        return todoService.create(todo);
    }

    public List<User> listUsers() {
        return userService.listUsers();
    }
    public List<Todo> listTodos(User user) {
        System.out.println(user.getId());
        return todoService.getByUserId(user.getId());
    }

    public Todo update(Todo todo) {
        return todoService.update(todo);
    }
}
