package org.campusmolndal;

import org.campusmolndal.interfaces.TodoDao;
import org.campusmolndal.interfaces.UserDao;
import org.campusmolndal.todo.Todo;
import org.campusmolndal.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AppControllerTest {
    private AppController sut;
    User sampleUser;
    Todo sampleTodo;
    @Mock
    private UserDao userDao;
    @Mock
    private TodoDao todoDao;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new AppController(userDao, todoDao);
        sampleUser = new User();
        sampleUser.setName("Test");
        sampleUser.setId("6491af8fd1fb19ed7588935c");
        sampleTodo = new Todo();
        sampleTodo.setUser("6491af8fd1fb19ed7588935c");
        sampleTodo.setText("Test");
        sampleTodo.setId("6491b07c6bf4316a6fe5c7a6");
        sampleTodo.setDone(false);
    }

    @Test
    void getUser() {
        //arrange
        User expected = sampleUser;
        when(userDao.read("6491af8fd1fb19ed7588935c")).thenReturn(sampleUser);

        //act
        User actual = sut.getUser("6491af8fd1fb19ed7588935c");

        //assert
        assertEquals(expected, actual);
    }
    @Test
    void getNullUser() {
        //arrange
        when(userDao.read(null)).thenReturn(null);

        //act
        User actual = sut.getUser(null);

        //assert
        assertNull(actual);
    }

    @Test
    void changeName() {
        //arrange
        String newName = "New name";
        sampleUser.setName(newName);
        User expected = sampleUser;
        when(userDao.update(sampleUser)).thenReturn(sampleUser);

        //act
        User actual = sut.changeName(sampleUser, newName);

        //assert
        assertEquals(expected, actual);
    }
    @Test
    void changeNameNullName() {
        //arrange
        String newName = null;

        //act
        User actual = sut.changeName(sampleUser, newName);

        //assert
        assertNull(actual);
    }
    @Test
    void changeNameNullUser() {
        //arrange
        String newName = "New name";
        sampleUser = null;

        //act
        User actual = sut.changeName(sampleUser, newName);

        //assert
        assertNull(actual);
    }

    @Test
    void delete() {
        //arrange
        when(todoDao.delete(sampleTodo)).thenReturn(sampleTodo);
        Todo expected = sampleTodo;

        //act
        Todo actual = sut.delete(sampleTodo);

        //assert
        assertEquals(expected, actual);
    }
    @Test
    void deleteNull() {
        //arrange
        sampleTodo = null;
        when(todoDao.delete(sampleTodo)).thenReturn(null);

        //act
        Todo actual = sut.delete(sampleTodo);

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
        sampleTodo = null;
        when(todoDao.create(sampleTodo)).thenReturn(null);

        //act
        Todo actual = sut.create(sampleTodo);

        //assert
        assertNull(actual);
    }

    @Test
    void testDelete() {
        //arrange
        when(userDao.delete(sampleUser)).thenReturn(sampleUser);
        User expected = sampleUser;

        //act
        User actual = sut.delete(sampleUser);

        //assert
        assertEquals(expected, actual);
    }
    @Test
    void testDeleteNull() {
        //arrange
        sampleUser = null;
        when(userDao.delete(sampleUser)).thenReturn(null);

        //act
        User actual = sut.delete(sampleUser);

        //assert
        assertNull(actual);
    }

    @Test
    void listUsers() {
        //arrange
        when(userDao.list()).thenReturn(Collections.singletonList(sampleUser));
        int expected = 1;

        //act
        int actual = sut.listUsers().size();

        //assert
        assertEquals(expected, actual);
    }

    @Test
    void listUsersNull() {
        //arrange
        when(userDao.list()).thenReturn(new ArrayList<>());
        int expected = 0;

        //act
        int actual = sut.listUsers().size();

        //assert
        assertEquals(expected, actual);
    }

    @Test
    void listUserTodo() {
        //arrange
        when(todoDao.getByUserId(sampleUser.getId())).thenReturn(Collections.singletonList(sampleTodo));
        int expected = 1;

        //act
        int actual = sut.listUserTodo(sampleUser).size();

        //assert
        assertEquals(expected, actual);
    }
    @Test
    void listUserTodoNull() {
        //arrange
        when(todoDao.getByUserId(sampleUser.getId())).thenReturn(new ArrayList<>());
        int expected = 0;

        //act
        int actual = sut.listUserTodo(sampleUser).size();

        //assert
        assertEquals(expected, actual);
    }
    @Test
    void listTodoInvalidId() {
        //arrange
        when(todoDao.getByUserId(null)).thenReturn(new ArrayList<>());
        int expected = 0;

        //act
        int actual = sut.listUserTodo(sampleUser).size();

        //assert
        assertEquals(expected, actual);
    }
    @Test
    void update() {
        //arrange
        when(todoDao.update(sampleTodo)).thenReturn(sampleTodo);
        Todo expected = sampleTodo;

        //act
        Todo actual = sut.update(sampleTodo);

        //assert
        assertEquals(expected, actual);
    }
    @Test
    void updateNull() {
        //arrange
        sampleTodo = null;
        when(todoDao.update(null)).thenReturn(null);

        //act
        Todo actual = sut.update(null);

        //assert
        assertNull(actual);
    }
}