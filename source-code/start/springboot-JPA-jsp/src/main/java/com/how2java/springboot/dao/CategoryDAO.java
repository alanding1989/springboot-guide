
package com.how2java.springboot.dao;

import com.how2java.springboot.pojo.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  Author      :   AlanDing
 *  Time        :   2019/9/29 下午9:55
 *  File        :   CategoryDAO.java
 *  Description :
 */

@Repository
public interface CategoryDAO extends JpaRepository<Category, Long> {
    // JPA 条件查询
    List<Category> findByName(String name);

    List<Category> findByNameLikeAndIdGreaterThanOrderByNameAsc(String name, Long id);
}
