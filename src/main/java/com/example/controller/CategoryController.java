package com.example.controller;

import com.example.pojo.Category;
import com.example.pojo.Result;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result add(@RequestBody @Validated (Category.Add.class)Category category) {
        categoryService.add(category);
        return Result.success();
    }

    @GetMapping
    public Result <List<Category>> List() {
        List<Category> cs = categoryService.list();
        return Result.success(cs);
    }

    @GetMapping
    public Result<Category> detail(@RequestParam Integer id) {
        Category c = categoryService.findById(id);
        return Result.success(c);
    }

    @PutMapping
    public Result update(@RequestBody @Validated (Category.Update.class) Category category) {
        categoryService.update(category);
        return Result.success();
    }

}
