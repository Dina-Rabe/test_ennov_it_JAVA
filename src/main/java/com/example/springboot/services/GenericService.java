package com.example.springboot.services;

import java.io.Serializable;
import java.util.Optional;

public interface GenericService <T, ID extends Serializable> {

    Optional<T> findById(ID id);

    T save(T entity);

    T update(ID id, T entity);

    Boolean delete(ID id);
}
