package org.campusmolndal.interfaces;

import java.util.List;

public interface Dao<T> {
    T create(T t);
    T read(String id);
    T update(T t);
    T delete(T t);
    List<T> list();
}