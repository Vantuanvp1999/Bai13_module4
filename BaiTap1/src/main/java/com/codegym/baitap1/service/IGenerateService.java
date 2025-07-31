package com.codegym.baitap1.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IGenerateService<T> {
    Page<T> findAll(Pageable pageable);
    Optional<T> findById(Long id);
    void save(T t);
    void delete(long id);
}
