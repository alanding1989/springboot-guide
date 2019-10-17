package com.how2java.springboot.web;

import com.how2java.springboot.dao.CategoryDAO;
import com.how2java.springboot.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  Author      :   AlanDing
 *  Time        :   2019/9/29 下午10:08
 *  File        :   CategoryController.java
 *  Description :
 */

@Controller
public class CategoryController {
    @Autowired
    private CategoryDAO categoryDAO;

    // 分页
    @RequestMapping("/listCategory")
    public String listCategory(Model m,
        @RequestParam(value = "start", defaultValue = "0") int start,
        @RequestParam(value = "size", defaultValue = "5") int size) {
        // 如果 start 为负，那么修改为0. 这个事情会发生在当前是首页，并点击了上一页的时候
        start = start < 0 ? 0 : start;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<Category> page = categoryDAO.findAll(pageable);
        m.addAttribute("page", page);
        return "listCategory";
    }

    @RequestMapping("/addCategory")
    public String addCategory(Category c) {
        categoryDAO.save(c);
        return "redirect:listCategory";
    }

    @RequestMapping("/deleteCategory")
    public String deleteCategory(Category c) {
        categoryDAO.delete(c);
        return "redirect:listCategory";
    }

    @RequestMapping("/updateCategory")
    public String updateCategory(Category c) {
        // 注意：JPA 新增和修改用的方法都是save. 它根据实体类的id是否为0来判断是进行增加还是修改
        categoryDAO.save(c);
        return "redirect:listCategory";
    }

    @RequestMapping("/editCategory")
    public String editCategory(Long id, Model m) {
        Category c = categoryDAO.getOne(id);
        m.addAttribute("c", c);
        return "editCategory";
    }
}
