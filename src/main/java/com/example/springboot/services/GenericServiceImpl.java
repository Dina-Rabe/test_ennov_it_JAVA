package com.example.springboot.services;

import com.example.springboot.repositories.GenericRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Optional;

public abstract class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T, ID> {

    protected  GenericRepository<T, ID> repository;

    @SuppressWarnings("null")
    @Override
    public Optional<T> findById(ID id) {
        Assert.notNull(id, "ID is mandatory");
        return repository.findById(id);
    }

    @SuppressWarnings("null")
    @Override
    public T save(T entity) {
        Assert.notNull(entity, "Entity is mandatory");

        return repository.save(entity);
    }

    @SuppressWarnings("null")
    @Override
    public T update(ID id, T entity) {
        Assert.notNull(id, "ID is mandatory");
        Assert.notNull(entity, "Entity is mandatory");

        Optional<T> entityOptional = repository.findById(id);
        if (entityOptional.isEmpty()) {
            throw new IllegalArgumentException("No record found with the given id " + id);
        }

        T toBeUpdated = entityOptional.get();
        BeanUtils.copyProperties(entity, toBeUpdated, "id");
        return save(entity);
    }

    @SuppressWarnings("null")
    @Override
    public Boolean delete(ID id) {
        Assert.notNull(id, "ID is mandatory");

        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("No entity found with the given id " + id);
        }

        repository.deleteById(id);

        return !repository.existsById(id);
    }
}
