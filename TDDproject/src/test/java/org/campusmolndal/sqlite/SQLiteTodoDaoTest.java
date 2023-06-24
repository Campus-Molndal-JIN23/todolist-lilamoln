package org.campusmolndal.sqlite;

import org.campusmolndal.todo.Todo;
import org.campusmolndal.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SQLiteTodoDaoTest {
    @Mock
    SQLiteHandler sqLiteHandler;
    SQLiteTodoDao sut;
    Todo sampleTodo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new SQLiteTodoDao(sqLiteHandler);
        sampleTodo = new Todo();
        sampleTodo.setText("Test");
        sampleTodo.setDone(false);
        sampleTodo.setId("1");
        sampleTodo.setUser("1");
    }

    @Test
    void create() {
        //Arrange
        String expected = sampleTodo.getText();
        when(sqLiteHandler.insert(sampleTodo)).thenReturn(sampleTodo);

        //Act
        String actual = sut.create(sampleTodo).getText();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void createNull() {
        //Arrange
        when(sqLiteHandler.insert((User) null)).thenReturn(null);

        //Act
        Todo actual = sut.create(null);

        //Assert
        assertNull(actual);
    }

    @Test
    void read() {
        //Arrange
        String expected = sampleTodo.getText();
        when(sqLiteHandler.getTodo(sampleTodo.getId())).thenReturn(sampleTodo);

        //Act
        String actual = sut.read(sampleTodo.getId()).getText();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void readNull() {
        //Arrange
        when(sqLiteHandler.getTodo(null)).thenReturn(null);

        //Act
        Todo actual = sut.read(null);

        //Assert
        assertNull(actual);
    }

    @Test
    void readWrongId() {
        //Arrange
        when(sqLiteHandler.getTodo("")).thenReturn(null);

        //Act
        Todo actual = sut.read("");

        //Assert
        assertNull(actual);
    }

    @Test
    void update() {
        //Arrange
        String expected = sampleTodo.getText();
        when(sqLiteHandler.update(sampleTodo)).thenReturn(sampleTodo);

        //Act
        String actual = sut.update(sampleTodo).getText();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void updateNull() {
        //Arrange
        when(sqLiteHandler.update((Todo) null)).thenReturn(null);

        //Act
        Todo actual = sut.update(null);

        //Assert
        assertNull(actual);
    }

    @Test
    void updateWrongId() {
        //Arrange
        when(sqLiteHandler.update(new Todo())).thenReturn(null);

        //Act
        Todo actual = sut.update(new Todo());

        //Assert
        assertNull(actual);
    }

    @Test
    void delete() {
        //Arrange
        String expected = sampleTodo.getText();
        when(sqLiteHandler.delete(sampleTodo)).thenReturn(sampleTodo);

        //Act
        String actual = sut.delete(sampleTodo).getText();

        //Assert
        assertEquals(expected, actual);
    }
    @Test
    void deleteNull() {
        //Arrange
        when(sqLiteHandler.delete((Todo) null)).thenReturn(null);

        //Act
        Todo actual = sut.delete(null);

        //Assert
        assertNull(actual);
    }

    @Test
    void deleteWrongId() {
        //Arrange
        when(sqLiteHandler.delete(new Todo())).thenReturn(null);

        //Act
        Todo actual = sut.delete(new Todo());

        //Assert
        assertNull(actual);
    }
    @Test
    void list() {
        //arrange
        String expected = sampleTodo.getText();
        when(sqLiteHandler.listTodos()).thenReturn(Collections.singletonList(sampleTodo));

        //act
        String actual = sut.list().get(0).getText();

        //assert
        assertEquals(expected, actual);
    }

    @Test
    void getByUserId() {
        //arrange
        String expected = sampleTodo.getText();
        when(sqLiteHandler.getTodosByUserId(sampleTodo.getUser())).thenReturn(Collections.singletonList(sampleTodo));

        //act
        String actual = sut.getByUserId(sampleTodo.getUser()).get(0).getText();

        //assert
        assertEquals(expected, actual);
    }
    @Test
    void getByUserIdNull() {
        //arrange
        when(sqLiteHandler.getTodosByUserId(null)).thenReturn(null);

        //act & assert
        assertNull(sut.getByUserId(null));
    }
    @Test
    void getByUserIdWrongId() {
        //arrange
        when(sqLiteHandler.getTodosByUserId("")).thenReturn(null);

        //assert
        assertNull(sut.getByUserId(""));
    }
}