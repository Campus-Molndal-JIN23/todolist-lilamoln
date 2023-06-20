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
        return getUser(userService.create(user).getId());
    }
    public Todo changeStatus(Todo todo, boolean status) {
        return todoService.changeStatus(todo, status);
    }
    public Todo changeText(Todo todo, String text) {
        return todoService.changeText(todo, text);
    }
    public Todo delete(Todo todo) {
        return todoService.delete(todo);
    }
    public Todo create(Todo todo) {
        return todoService.create(todo);
    }

    public List<User> listUsers() {
        List<User> users = new ArrayList<>();
        for(User user : userService.listUsers()) {
            users.add(getUser(user.getId()));
        }
        return users;
    }
    public List<Todo> listTodos() {
        return todoService.listTodos();
    }
    public List<Todo> listTodos(User user) {
        return todoService.getByUserId(user.getId());
    }

    public Todo update(Todo todo) {
        return todoService.update(todo);
    }
}
