
package com.how2java.springboot.service;

import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;

/**
 *  Author      :   AlanDing
 *  Time        :   2019/10/1 下午12:48
 *  File        :   CategoryService.java
 *  Description :
 */


public interface RedisService<T> {
    void insert(T category);

    void deleteById(int id);

    void update(T category);

    T findById(int id);

    PageInfo<T> findAll(Pageable pageable);
}
