package com.example.service;

import com.example.pojo.Category;

import java.util.List;

public interface CategoryService {
    void add(Category category);

    //列表查詢
    List<Category> list();
    //查詢by id
    Category findById(Integer id);

    void update(Category category);
}
