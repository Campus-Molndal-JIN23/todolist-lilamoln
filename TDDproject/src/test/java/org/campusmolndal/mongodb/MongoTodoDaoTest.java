package org.campusmolndal.mongodb;

import org.campusmolndal.interfaces.TodoDao;
import org.campusmolndal.mongodb.MongoFacade;
import org.campusmolndal.mongodb.MongoTodoDao;
import org.campusmolndal.todo.Todo;
import org.bson.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class MongoTodoDaoTest {
    private MongoTodoDao sut;
    Todo sampleTodo;
    @Mock
    private MongoFacade mongoFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new MongoTodoDao();
        sampleTodo = new Todo();
        sampleTodo.setId("6491b07c6bf4316a6fe5c7a6");
        sampleTodo.setDone(false);
        sampleTodo.setText("Test");
        sampleTodo.setUser("6491af8fd1fb19ed7588935c");
    }

    @Test
    public void testCreate() {
        //arrange
        String expected = sampleTodo.getText();
        when(mongoFacade.insert(sampleTodo.toDocument())).thenReturn(sampleTodo.toDocument());

        //act
        String actual = sut.create(sampleTodo).getText();

        //assert
        assertEquals(expected, actual);
    }

    @Test
    public void testRead() {

    }
}