package com.turntabl.testsystem.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DAO<T> {
    T get(UUID id);

    List<T> addAll(List<T> t);

    List<T> getAll();

    T add(T t);

    T update(T t, String[] params);

    boolean delete(T t);
}
