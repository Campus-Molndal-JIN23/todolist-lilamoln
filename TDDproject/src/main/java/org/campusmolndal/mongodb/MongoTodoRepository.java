package org.campusmolndal.mongodb;

import org.campusmolndal.todo.TodoDao;

public class MongoTodoRepository extends MongoRepository<TodoDao> {
    public MongoTodoRepository() {
        super("todos", TodoDao.class);
    }
}