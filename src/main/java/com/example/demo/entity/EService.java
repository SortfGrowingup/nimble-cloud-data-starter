package com.example.demo.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author SortfGrowingup
 * @version 1.0
 * @date 2021/1/21 12:37
 */
@Service
public class EService{
    @Autowired
    private EDao eDao;
    public E save(E e){
        return eDao.save(e);
    }
    public Optional<E> findOne(@Nullable Specification<E> var1){
        return eDao.findOne(var1);
    }

    public List<E> findAll(@Nullable Specification<E> var1){
        return eDao.findAll(var1);
    }

    public Page<E> findAll(@Nullable Specification<E> var1, Pageable var2){
        return eDao.findAll(var1,var2);
    }

    public List<E> findAll(@Nullable Specification<E> var1, Sort var2){
        return eDao.findAll(var1,var2);
    }

    public long count(@Nullable Specification<E> var1){
        return eDao.count(var1);
    }
}