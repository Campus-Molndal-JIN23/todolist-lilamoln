package org.campusmolndal.mongodb;

import org.campusmolndal.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MongoUserDaoTest {
    MongoUserDao sut;
    User sampleUser;
    @Mock
    MongoFacade mongoFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new MongoUserDao();
        sampleUser = new User();
        sampleUser.setName("Test");
        sampleUser.setId("6491af8fd1fb19ed7588935c");
        sampleUser.setTodos(new ArrayList<>());
    }

    @Test
    void create() {
        //arrange
        String expected = sampleUser.getName();
        when(mongoFacade.insert(sampleUser.toDocument())).thenReturn(sampleUser.toDocument());

        //act
        String actual = sut.create(sampleUser).getName();

        //assert
        assertEquals(expected, actual);
    }
    @Test
    void createNullUser() {
        //arrange
        when(mongoFacade.insert(null)).thenReturn(null);

        //act
        User actual = sut.create(null);

        //assert
        assertNull(actual);
    }

    @Test
    void read() {
        //arrange
        String expected = sampleUser.getName();
        when(mongoFacade.get("6491af8fd1fb19ed7588935c")).thenReturn(sampleUser.toDocument());

        //act
        String actual = sut.read("6491af8fd1fb19ed7588935c").getName();

        //assert
        assertEquals(expected, actual);
    }
    @Test
    void readNullId() {
        //arrange
        when(mongoFacade.get(null)).thenReturn(null);

        //act
        User actual = sut.read(null);

        //assert
        assertNull(actual);
    }
    @Test
    void readWrongId() {
        //arrange
        when(mongoFacade.get("invalid id")).thenReturn(null);

        //act
        User actual = sut.read("invalid id");

        //assert
        assertNull(actual);
    }

    @Test
    void update() {
        //arrange
        String expected = sampleUser.getName();
        when(mongoFacade.update(sampleUser.toDocument())).thenReturn(sampleUser.toDocument());

        //act
        String actual = sut.update(sampleUser).getName();

        //assert
        assertEquals(expected, actual);
    }
    @Test
    void updateNull() {
        //arrange
        when(mongoFacade.update(null)).thenReturn(null);

        //act
        User actual = sut.update(null);

        //assert
        assertNull(actual);
    }
    @Test
    void updateWrongId() {
        //arrange
        when(mongoFacade.update(sampleUser.toDocument())).thenReturn(null);

        //act
        User actual = sut.update(sampleUser);

        //assert
        assertNull(actual);
    }

    @Test
    void delete() {
        //arrange
        String expected = sampleUser.getName();
        when(mongoFacade.delete(sampleUser.toDocument())).thenReturn(sampleUser.toDocument());

        //act
        String actual = sut.delete(sampleUser).getName();

        //assert
        assertEquals(expected, actual);
    }
    @Test
    void deleteNull() {
        //arrange
        when(mongoFacade.delete(null)).thenReturn(null);

        //act
        User actual = sut.delete(null);

        //assert
        assertNull(actual);
    }
    @Test
    void deleteWrongId() {
        //arrange
        sampleUser.setId("invalid id");
        when(mongoFacade.delete(sampleUser.toDocument())).thenReturn(null);

        //act
        User actual = sut.delete(sampleUser);

        //assert
        assertNull(actual);
    }
}