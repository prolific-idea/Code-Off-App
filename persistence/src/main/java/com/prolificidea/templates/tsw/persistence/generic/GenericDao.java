package com.prolificidea.templates.tsw.persistence.generic;

import java.util.List;

public interface GenericDao<T> {

    T find(Object id);
    List<T> findAll();
    List<T> findAll(int pageSize, int pageNumber);
    List<T> search(String property, String criteria);
    List<T> search(String property, String criteria, int pageSize, int pageNumber);
    long count();
    void delete(Object id);
    T create(T t);
    T update(T t);

}
