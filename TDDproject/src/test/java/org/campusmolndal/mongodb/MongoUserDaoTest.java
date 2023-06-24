package org.campusmolndal.mongodb;

import org.campusmolndal.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MongoUserDaoTest {
    @Mock
    MongoFacade mongoFacade;
    MongoUserDao sut;
    User sampleUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new MongoUserDao(mongoFacade);
        sampleUser = new User();
        sampleUser.setName("Test");
        sampleUser.setId("6491af8fd1fb19ed7588935c");
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
    void createNull() {
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
        when(mongoFacade.get(sampleUser.getId())).thenReturn(sampleUser.toDocument());

        //act
        String actual = sut.read(sampleUser.getId()).getName();

        //assert
        assertEquals(expected, actual);
    }

    @Test
    void readWrongId() {
        //arrange
        when(mongoFacade.get("wrongId")).thenReturn(null);

        //act
        User actual = sut.read("wrongId");

        //assert
        assertNull(actual);
    }
    @Test
    void readNull() {
        //arrange
        when(mongoFacade.get(null)).thenReturn(null);

        //act
        User actual = sut.read(null);

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
        when(mongoFacade.delete(sampleUser.toDocument())).thenReturn(null);

        //act
        User actual = sut.delete(sampleUser);

        //assert
        assertNull(actual);
    }

    @Test
    void list() {
        //arrange
        String expected = sampleUser.getName();
        when(mongoFacade.list()).thenReturn(Collections.singletonList(sampleUser.toDocument()));

        //act
        String actual = sut.list().get(0).getName();

        //assert
        assertEquals(expected, actual);
    }
}