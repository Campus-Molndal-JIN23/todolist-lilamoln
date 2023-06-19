package org.campusmolndal.interfaces;

import java.util.List;

public interface Repository<T> {
    void create(T t);
    T read(String id);
    void update(T t);
    void delete(T t);
    List<T> list();
    List<T> search(String field, String term);
}