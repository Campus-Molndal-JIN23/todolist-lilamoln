package org.campusmolndal.interfaces;

import java.util.List;

public interface Dao<T> {
    T create(T t);
    T read(String id);
    T update(String id, T t);
    T delete(String id);
    List<T> list();
}