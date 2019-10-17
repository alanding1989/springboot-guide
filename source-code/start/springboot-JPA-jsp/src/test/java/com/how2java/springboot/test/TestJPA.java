package com.how2java.springboot.test;

import com.how2java.springboot.Application;
import com.how2java.springboot.dao.CategoryDAO;
import com.how2java.springboot.pojo.Category;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestJPA {
    @Autowired
    CategoryDAO dao;

    @Before
    public void before() {
        List<Category> cs = dao.findAll();
        // cs.forEach(c -> dao.delete(c));
        for (int i = 0; i < 10; i++) {
            Category c = new Category();
            c.setName("Category " + i);
            dao.save(c);
        }
    }

    @Test
    public void test1() {
        System.out.println("所有的分类信息： ");
        dao.findAll().forEach(c -> System.out.println(c.getName()));
        System.out.println();
    }

    @Test
    public void test2() {
        System.out.println("查询名称是　\"Category 1 \"的f分类： ");
        dao.findByName("Category 1")
           .forEach(c -> System.out.println("c.getName(): " + c.getName()));
        System.out.println();
    }

    @Test
    public void test3() {
        System.out.println("根据名称模糊查询，id 大于5, 并且名称正排序查询");
        dao.findByNameLikeAndIdGreaterThanOrderByNameAsc("%3%", 5L)
           .forEach(c -> System.out.println(c + "\n"));
    }
}