package org.campusmolndal.user;

import org.campusmolndal.interfaces.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceTest {
    UserService sut;
    User sampleUser;
    @Mock
    UserDao userDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new UserService(userDao);
        sampleUser = new User();
        sampleUser.setName("Test");
        sampleUser.setId("6491af8fd1fb19ed7588935c");
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
    void getUserNull() {
        //arrange
        when(userDao.read(null)).thenReturn(null);

        //act
        User actual = sut.getUser(null);

        //assert
        assertNull(actual);
    }

    @Test
    void getUserWrongId() {
        //arrange
        when(userDao.read("invalid id")).thenReturn(null);

        //act
        User actual = sut.getUser("invalid id");

        //assert
        assertNull(actual);
    }

    @Test
    void create() {
        //arrange
        User expected = sampleUser;
        when(userDao.create(sampleUser)).thenReturn(sampleUser);

        //act
        User actual = sut.create(sampleUser);

        //assert
        assertEquals(expected, actual);
    }
    @Test
    void createNull() {
        //arrange
        when(userDao.create(null)).thenReturn(null);

        //act
        User actual = sut.create(null);

        //assert
        assertNull(actual);
    }
    @Test
    void changeName() {
        //arrange
        sampleUser.setName("Test");
        User expected = sampleUser;
        when(userDao.update(sampleUser)).thenReturn(sampleUser);

        //act
        User actual = sut.changeName(sampleUser, "Test");

        //assert
        assertEquals(expected, actual);
    }
    @Test
    void changeNameNull() {
        //arrange
        when(userDao.update(null)).thenReturn(null);

        //act
        User actual = sut.changeName(null, "Test");

        //assert
        assertNull(actual);
    }
    @Test
    void deleteUser() {
        //arrange
        User expected = sampleUser;
        when(userDao.delete(sampleUser)).thenReturn(sampleUser);

        //act
        User actual = sut.deleteUser(sampleUser);

        //assert
        assertEquals(expected, actual);
    }
    @Test
    void deleteUserNull() {
        //arrange
        when(userDao.delete(null)).thenReturn(null);

        //act
        User actual = sut.deleteUser(null);

        //assert
        assertNull(actual);
    }
    @Test
    void deleteUserWrongId() {
        //arrange
        when(userDao.delete(sampleUser)).thenReturn(null);

        //act
        User actual = sut.deleteUser(sampleUser);

        //assert
        assertNull(actual);
    }

    @Test
    void listUsers() {
        //arrange
        when(userDao.list()).thenReturn(Arrays.asList(sampleUser));
        int expected = 1;

        //act
        int actual = sut.listUsers().size();

        //assert
        assertEquals(expected, actual);
    }
    @Test
    void listUsersNoUsers() {
        //arrange
        when(userDao.list()).thenReturn(Arrays.asList());
        int expected = 0;

        //act
        int actual = sut.listUsers().size();

        //assert
        assertEquals(expected, actual);
    }
}