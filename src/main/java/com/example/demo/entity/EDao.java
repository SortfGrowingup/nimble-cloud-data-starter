package com.example.demo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author SortfGrowingup
 * @version 1.0
 * @date 2021/1/21 12:34
 */
@Repository
public interface EDao extends JpaRepository<E, String>, JpaSpecificationExecutor<E> { }