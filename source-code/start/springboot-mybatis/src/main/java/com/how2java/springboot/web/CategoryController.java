package com.how2java.springboot.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.springboot.pojo.Category;
import com.how2java.springboot.service.impl.CategoryRedisImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CategoryController {
    private CategoryRedisImpl categoryRedisService;

    @PostMapping("/category")
    public String addCategory(Category c) throws Exception {
        categoryRedisService.insert(c);
        return "redirect:categories";
    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategory(Category c) throws Exception {
        categoryRedisService.deleteById(c.getId());
        return "redirect:categories";
    }

    @PutMapping("/categories/{id}")
    public String updateCategory(Category c) throws Exception {
        categoryRedisService.update(c);
        return "redirect:categories";
    }

    @GetMapping("/categories/{id}")
    public String listCategory(@PathVariable("id") int id, Model m) throws Exception {
        Category c = categoryRedisService.findById(id);
        m.addAttribute("c", c);
        return "editCategory";
    }

    @GetMapping("/categories")
    public String listCategory(Model m,
                               @RequestParam(value = "index", defaultValue = "0") int index,
                               @RequestParam(value = "size", defaultValue = "5") int size)
        throws Exception {
        PageHelper.startPage(index, size);
        PageInfo<Category> page = categoryRedisService.findAll(PageRequest.of(index, size));
        m.addAttribute("page", page);
        return "listCategory";
    }

    // 没有redis，pagehelper实现分页，直接用ORM从数据库取。
    // @GetMapping("/categories")
    // public String listCategory(Model m,
    //                            @RequestParam(value = "start", defaultValue = "0") int start,
    //                            @RequestParam(value = "size", defaultValue = "5") int size)
    //     throws Exception {
    //     PageHelper.startPage(start, size, "id desc");
    //     List<Category> cs = categoryMapper.findAll();
    //     PageInfo<Category> page = new PageInfo<>(cs);
    //     m.addAttribute("page", page);
    //     return "listCategory";
    // }
}
