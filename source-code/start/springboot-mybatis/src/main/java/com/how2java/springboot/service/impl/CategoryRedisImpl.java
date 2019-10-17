package com.how2java.springboot.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.how2java.springboot.mapper.MybatisMapper;
import com.how2java.springboot.pojo.Category;
import com.how2java.springboot.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *  Author      :   AlanDing
 *  Time        :   2019/10/1 下午12:49
 *  File        :   CategoryServiceImpl.java
 *  Description :
 */

@Service("Redis")
@CacheConfig(cacheNames = "category")
public class CategoryRedisImpl implements RedisService<Category>{
    @Autowired
    MybatisMapper<Category> categoryDAO;

    @Override
    @CacheEvict(allEntries = true)
    // @CacheEvict(key = "'category ' + #p0")
    public void insert(Category category) {
        // TODO:Auto generated method stub
        categoryDAO.insert(category);
    }

    @Override
    @CacheEvict(allEntries = true)
    // @CacheEvict(key = "'category ' + #p0")
    public void deleteById(int id) {
        // TODO:Auto generated method stub
        categoryDAO.deleteById(id);
    }

    @Override
    @CacheEvict(allEntries = true)
    // @CacheEvict(key = "'category ' + #p0")
    public void update(Category category) {
        // TODO:Auto generated method stub
        categoryDAO.update(category);
    }

    @Override
    @Cacheable(key = "'category ' + #p0")
    public Category findById(int id) {
        return categoryDAO.findById(id);
    }

    @Override
    @Cacheable(key = "'category ' +#p0.offset+ '-' + #p0.pageSize ")
    public PageInfo<Category> findAll(Pageable pageable) {
        Page<Category> pageFromMybatis = categoryDAO.findAll();
        return new PageInfo<>(pageFromMybatis, 5);
    }
}
