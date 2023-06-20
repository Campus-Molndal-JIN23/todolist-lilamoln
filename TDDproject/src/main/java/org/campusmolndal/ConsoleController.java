package org.campusmolndal;

import org.campusmolndal.mongodb.MongoTodoDao;
import org.campusmolndal.mongodb.MongoUserDao;
import org.campusmolndal.todo.Todo;
import org.campusmolndal.user.User;

import java.rmi.ServerError;
import java.util.List;

public class ConsoleController {
    private final AppController appController;
    private User user;
    private boolean running = true;

    public ConsoleController() {
        this.appController = setAppController();
        while(this.user == null) this.user = setUser();
        while (running) {
            mainMenu();
        }
    }

    private void mainMenu() {
        System.out.println("Welcome " + user.getName());
        System.out.println("User id: " + user.getId());
        System.out.println("1. List todos (" + user.getTodos().size() + ")");
        System.out.println("2. Create todo");
        System.out.println("3. Change name");
        System.out.println("4. Delete user");
        System.out.println("5. Change user");
        System.out.println("6. Exit");
        switch (InputGetter.getIntInput("Enter choice", 1, 8)) {
            case 1 -> {
                if(user.getTodos() == null) user.setTodos(appController.listTodos(user));
                listTodos(user.getTodos());
            }
            case 2 -> {
                createTodo();
            }
            case 3 -> {
                User newName = appController.changeName(user, InputGetter.getStringInput("Enter new name"));
                if(newName == null) {
                    System.err.println("Error changing name");
                    break;
                }
                this.user = appController.getUser(newName.getId());
            }
            case 4 -> {
                if(appController.delete(user) == null) {
                    System.err.println("Error deleting user");
                    break;
                }
                this.user = null;
                while(this.user == null) this.user = setUser();
            }
            case 5 -> {
                this.user=setUser();
            }
            case 6 -> {
                running = false;
            }
        }
    }

    private void createTodo() {
        Todo todo = new Todo();
        todo.setText(InputGetter.getStringInput("Enter text"));
        todo.setDone(InputGetter.getBooleanInput("Is it done?"));
        todo.setUser(user.getId());
        user.getTodos().add(appController.create(todo));
    }

    private void listTodos(List<Todo> todos) {
        if(todos == null) {
            System.out.println("No todos found");
            return;
        }
        for (int i = 0; i < todos.size(); i++) {
            System.out.println((i + 1) + ". " + todos.get(i).getText() + " (" + (todos.get(i).isDone() ? "Done" : "Not done") + ")");
        }
        System.out.println((todos.size() + 1) + ". Back");
        int choice = InputGetter.getIntInput("Enter choice", 1, todos.size() + 1);
        if(choice == todos.size() + 1) return;
        todoMenu(todos.get(choice - 1));
    }

    private void todoMenu(Todo todo) {
        if(todo == null) return;
        System.out.println("1. Change text");
        System.out.println("2. Change done");
        System.out.println("3. Delete todo");
        System.out.println("4. Back");
        switch (InputGetter.getIntInput("Enter choice", 1, 4)) {
            case 1 -> {
                todo.setText(InputGetter.getStringInput("Enter text"));
                appController.update(todo);
            }
            case 2 -> {
                todo.setDone(!todo.isDone());
                appController.update(todo);
            }
            case 3 -> {
                if(appController.delete(todo) == null) {
                    System.err.println("Todo not deleted, try again");
                }
                else {
                    user.getTodos().remove(todo);
                    System.out.println("Todo deleted");
                }
            }
            case 4 -> {
                return;
            }
        }
    }

    private User setUser() {
        System.out.println("1. Select user");
        System.out.println("2. Create user");
        switch (InputGetter.getIntInput("Enter choice", 1, 2)) {
            case 1 -> {
                return selectUser();
            }
            case 2 -> {
                return createUser();
            }
            default -> {
                return null;
            }
        }
    }

    private User createUser() {
        User user = new User();
        user.setName(InputGetter.getStringInput("Enter name"));
        return appController.create(user);
    }

    private User selectUser() {
        List<User> users = appController.listUsers();
        if (users.size() == 0) {
            System.out.println("No users found");
            return null;
        }
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ". " + users.get(i).getName());
        }
        System.out.println((users.size() + 1) + ". Back");
        int choice = InputGetter.getIntInput("Enter choice", 1, users.size() + 1);
        if (choice == users.size() + 1) {
            return null;
        }
        return users.get(choice - 1);
    }

    private AppController setAppController() {
        System.out.println("Select database type:");
        System.out.println("1. MongoDB");
        System.out.println("2. NoSQL");
        switch (InputGetter.getIntInput("Enter choice", 1, 2)) {
            case 1 -> {
                return new AppController(new MongoUserDao(), new MongoTodoDao());
            }
            case 2 -> {
                return null;
            }
            default -> {
                return null;
            }
        }
    }
}
