package com.example.springrest.service;

import com.example.springrest.entity.Category;

import java.util.List;

public interface CRUDService <T>{
    T findById(Long id);

    List<T> findAll();

    T insert(T item);

    T update(T item);

    void deleteById(Long id);
}
