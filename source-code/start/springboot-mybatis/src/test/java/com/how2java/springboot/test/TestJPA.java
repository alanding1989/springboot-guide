package com.how2java.springboot.test;

import com.how2java.springboot.Application;
import com.how2java.springboot.mapper.MybatisMapper;
import com.how2java.springboot.pojo.Category;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *  Author      :   AlanDing
 *  Time        :   2019/9/30 下午8:26
 *  File        :   TestJPA.java
 *  Description :
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestJPA {
    @Autowired
    MybatisMapper categoryMapper;

    @Test
    public void test() {
        List<Category> cs = categoryMapper.findAll();
        cs.forEach(c -> System.out.println("c.getName(): " + c.getName()));
    }
}
