package org.campusmolndal.mongodb;

import org.campusmolndal.todo.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MongoTodoDaoTest {
    @Mock
    private MongoFacade mongoFacade;
    private MongoTodoDao sut;
    private Todo sampleTodo;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new MongoTodoDao(mongoFacade);
        sampleTodo = new Todo();
        sampleTodo.setText("Test");
        sampleTodo.setDone(false);
        sampleTodo.setId("6491b07c6bf4316a6fe5c7a6");
        sampleTodo.setUser("6491af8fd1fb19ed7588935c");
    }

    @Test
    void create() {
        //Arrange
        String expected = sampleTodo.getText();
        when(mongoFacade.insert(sampleTodo.toDocument())).thenReturn(sampleTodo.toDocument());

        //Act
        String actual = sut.create(sampleTodo).getText();

        //Assert
        assertEquals(expected, actual);
    }
    @Test
    void createNull() {
        //Arrange
        when(mongoFacade.insert(null)).thenReturn(null);

        //Act
        Todo actual = sut.create(null);

        //Assert
        assertNull(actual);
    }

    @Test
    void read() {
        //Arrange
        String expected = sampleTodo.getText();
        when(mongoFacade.get(sampleTodo.getId())).thenReturn(sampleTodo.toDocument());

        //Act
        String actual = sut.read(sampleTodo.getId()).getText();

        //Assert
        assertEquals(expected, actual);
    }
    @Test
    void readNull() {
        //Arrange
        when(mongoFacade.get(null)).thenReturn(null);

        //Act
        Todo actual = sut.read(null);

        //Assert
        assertNull(actual);
    }

    @Test
    void readWrongId() {
        //Arrange
        when(mongoFacade.get("Not a valid ObjectId")).thenReturn(null);

        //Act
        Todo actual = sut.read("Not a valid ObjectId");

        //Assert
        assertNull(actual);
    }

    @Test
    void update() {
        //Arrange
        String expected = sampleTodo.getText();
        when(mongoFacade.update(sampleTodo.toDocument())).thenReturn(sampleTodo.toDocument());

        //Act
        String actual = sut.update(sampleTodo).getText();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void updateNull() {
        //Arrange
        when(mongoFacade.update(null)).thenReturn(null);

        //Act
        Todo actual = sut.update(null);

        //Assert
        assertNull(actual);
    }

    @Test
    void updateWrongId() {
        //Arrange
        when(mongoFacade.update(sampleTodo.toDocument())).thenReturn(null);

        //Act
        Todo actual = sut.update(sampleTodo);

        //Assert
        assertNull(actual);
    }

    @Test
    void delete() {
        //Arrange
        String expected = sampleTodo.getText();
        when(mongoFacade.delete(sampleTodo.toDocument())).thenReturn(sampleTodo.toDocument());

        //Act
        String actual = sut.delete(sampleTodo).getText();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void deleteNull() {
        //Arrange
        when(mongoFacade.delete(null)).thenReturn(null);

        //Act
        Todo actual = sut.delete(null);

        //Assert
        assertNull(actual);
    }

    @Test void deleteWrongId() {
        //Arrange
        when(mongoFacade.delete(sampleTodo.toDocument())).thenReturn(null);

        //Act
        Todo actual = sut.delete(sampleTodo);

        //Assert
        assertNull(actual);
    }

    @Test
    void list() {
        //Arrange
        String expected = sampleTodo.getText();
        when(mongoFacade.list()).thenReturn(Collections.singletonList(sampleTodo.toDocument()));

        //Act
        String actual = sut.list().get(0).getText();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void listNull() {
        //Arrange
        when(mongoFacade.list()).thenReturn(null);

        //Assert
        assertNull(sut.list());
    }

    @Test
    void getByUserId() {
        //Arrange
        String expected = sampleTodo.getText();
        when(mongoFacade.findTodoByUserId(sampleTodo.getUser())).thenReturn(Collections.singletonList(sampleTodo.toDocument()));

        //Act
        String actual = sut.getByUserId(sampleTodo.getUser()).get(0).getText();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getByUserIdNull() {
        //Arrange
        when(mongoFacade.findTodoByUserId(sampleTodo.getUser())).thenReturn(null);

        //Assert
        assertNull(sut.getByUserId(sampleTodo.getUser()));
    }

    @Test
    void getByUserIdWrongId() {
        //Arrange
        when(mongoFacade.findTodoByUserId(sampleTodo.getUser())).thenReturn(null);

        //Assert
        assertNull(sut.getByUserId(sampleTodo.getUser()));
    }
}