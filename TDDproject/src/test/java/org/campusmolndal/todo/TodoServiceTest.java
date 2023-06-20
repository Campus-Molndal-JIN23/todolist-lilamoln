package org.campusmolndal.todo;

import org.campusmolndal.interfaces.TodoDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class TodoServiceTest {
    private TodoService sut;
    @Mock
    private TodoDao todoDao;
    private Todo sampleTodo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new TodoService(todoDao);
        sampleTodo = new Todo();
        sampleTodo.setId("6491b07c6bf4316a6fe5c7a6");
        sampleTodo.setUser("6491af8fd1fb19ed7588935c");
        sampleTodo.setDone(false);
        sampleTodo.setText("Test");
    }
    @Test
    void delete() {
        //arrange
        when(todoDao.delete(sampleTodo.getId())).thenReturn(sampleTodo);
        Todo expected = sampleTodo;

        //act
        Todo actual = sut.delete(sampleTodo);

        //assert
        assertEquals(expected, actual);
    }
    @Test
    void deleteNotValidObjectId() {
        //arrange
        Todo expected = null;
        sampleTodo.setId("Not a valid ObjectId");
        when(todoDao.delete(sampleTodo.getId())).thenReturn(null);

        //act
        Todo actual = sut.delete(sampleTodo);

        //assert
        assertNull(actual);
    }
    @Test
    void deleteNull() {
        //arrange
        Todo expected = null;
        when(todoDao.delete(null)).thenReturn(null);

        //act
        Todo actual = sut.delete(null);

        //assert
        assertNull(actual);
    }

    @Test
    void create() {
        //arrange
        when(todoDao.create(sampleTodo)).thenReturn(sampleTodo);
        Todo expected = sampleTodo;

        //act
        Todo actual = sut.create(sampleTodo);

        //assert
        assertEquals(expected, actual);
    }
    @Test
    void createNull() {
        //arrange
        Todo expected = null;
        when(todoDao.create(null)).thenReturn(null);

        //act
        Todo actual = sut.create(null);

        //assert
        assertNull(actual);
    }
    @Test
    void createInvalidObjectId() {
        //arrange
        sampleTodo.setUser("Not a valid ObjectId");
        when(todoDao.create(sampleTodo)).thenReturn(null);

        //act
        Todo actual = sut.create(sampleTodo);

        //assert
        assertNull(actual);
    }
    @Test
    void getByUserId() {
        //arrange
        List<Todo> expected = Arrays.asList(sampleTodo);
        when(todoDao.getByUserId(sampleTodo.getUser())).thenReturn(expected);

        //act
        List<Todo> actual = sut.getByUserId(sampleTodo.getUser());

        //assert
        assertEquals(expected, actual);
    }
    @Test
    void getByInvalidUserId() {
        //arrange
        when(todoDao.getByUserId("Not a valid ObjectId")).thenReturn(null);

        //act
        List<Todo> actual = sut.getByUserId("Not a valid ObjectId");

        //assert
        assertNull(actual);
    }
    @Test
    void getByNullUserId() {
        //arrange
        when(todoDao.getByUserId(null)).thenReturn(null);

        //act
        List<Todo> actual = sut.getByUserId(null);

        //assert
        assertNull(actual);
    }

    @Test
    void listTodos() {
        //arrange
        List<Todo> expected = Arrays.asList(sampleTodo);
        when(todoDao.list()).thenReturn(expected);
        //act
        List<Todo> actual = sut.listTodos();
        //assert
        assertEquals(expected, actual);
    }

    @Test
    void update() {
        //arrange
        when(todoDao.update(sampleTodo.getId(), sampleTodo)).thenReturn(sampleTodo);
        Todo expected = sampleTodo;
        //act
        Todo actual = sut.update(sampleTodo);
        //assert
        assertEquals(expected, actual);
    }
    @Test
    void updateNull() {
        //arrange
        Todo expected = null;
        when(todoDao.update(null, null)).thenReturn(null);
        //act
        Todo actual = sut.update(null);
        //assert
        assertNull(actual);
    }
    @Test
    void updateInvalidObjectId() {
        //arrange
        sampleTodo.setId("Not a valid ObjectId");
        when(todoDao.update(sampleTodo.getId(), sampleTodo)).thenReturn(null);
        //act
        Todo actual = sut.update(sampleTodo);
        //assert
        assertNull(actual);
    }
}