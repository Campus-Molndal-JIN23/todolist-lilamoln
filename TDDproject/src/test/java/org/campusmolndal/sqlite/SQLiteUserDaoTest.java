package org.campusmolndal.sqlite;

import org.campusmolndal.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SQLiteUserDaoTest {
    @Mock
    SQLiteHandler sqLiteHandler;
    SQLiteUserDao sut;
    User sampleUser = new User();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new SQLiteUserDao(sqLiteHandler);
        sampleUser.setId("1");
        sampleUser.setName("Test");
    }

    @Test
    void create() {
        //Arrange
        String expected = sampleUser.getName();
        when(sqLiteHandler.insert(sampleUser)).thenReturn(sampleUser);

        //Act
        String actual = sut.create(sampleUser).getName();

        //Assert
        assertEquals(expected, actual);
    }
    @Test
    void createNull() {
        //Arrange
        when(sqLiteHandler.insert((User) null)).thenReturn(null);

        //Act
        User actual = sut.create(null);

        //Assert
        assertNull(actual);
    }

    @Test
    void read() {
        //Arrange
        String expected = sampleUser.getName();
        when(sqLiteHandler.getUser(sampleUser.getId())).thenReturn(sampleUser);

        //Act
        String actual = sut.read(sampleUser.getId()).getName();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void readNull() {
        //Arrange
        when(sqLiteHandler.getUser(sampleUser.getId())).thenReturn(null);

        //Act
        User actual = sut.read(sampleUser.getId());

        //Assert
        assertNull(actual);
    }

    @Test
    void readWrongId() {
        //Arrange
        when(sqLiteHandler.getUser(sampleUser.getId())).thenReturn(null);

        //Act
        User actual = sut.read(sampleUser.getId());

        //Assert
        assertNull(actual);
    }

    @Test
    void update() {
        //Arrange
        String expected = sampleUser.getName();
        when(sqLiteHandler.update(sampleUser)).thenReturn(sampleUser);

        //Act
        String actual = sut.update(sampleUser).getName();

        //Assert
        assertEquals(expected, actual);
    }
    @Test
    void updateNull() {
        //Arrange
        when(sqLiteHandler.update((User) null)).thenReturn(null);

        //Act
        User actual = sut.update(null);

        //Assert
        assertNull(actual);
    }
    @Test
    void updateWrongId() {
        //Arrange
        when(sqLiteHandler.update(sampleUser)).thenReturn(null);

        //Act
        User actual = sut.update(sampleUser);

        //Assert
        assertNull(actual);
    }

    @Test
    void delete() {
        //Arrange
        String expected = sampleUser.getName();
        when(sqLiteHandler.delete(sampleUser)).thenReturn(sampleUser);

        //Act
        String actual = sut.delete(sampleUser).getName();

        //Assert
        assertEquals(expected, actual);
    }
    @Test
    void deleteNull() {
        //Arrange
        when(sqLiteHandler.delete((User) null)).thenReturn(null);

        //Act
        User actual = sut.delete(null);

        //Assert
        assertNull(actual);
    }
    @Test
    void deleteWrongId() {
        //Arrange
        when(sqLiteHandler.delete(sampleUser)).thenReturn(null);

        //Act
        User actual = sut.delete(sampleUser);

        //Assert
        assertNull(actual);
    }
    @Test
    void list() {
        //Arrange
        when(sqLiteHandler.listUsers()).thenReturn(Collections.singletonList(sampleUser));

        //Act
        int actual = sut.list().size();

        //Assert
        assertEquals(1, actual);
    }
}