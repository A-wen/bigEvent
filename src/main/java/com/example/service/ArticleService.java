package com.example.service;

import com.example.pojo.Article;
import com.example.pojo.PageBean;

public interface ArticleService {
     void add(Article article);

     //條件分頁列表查詢
     PageBean<Article> list(Integer pageNo, Integer pageSize ,Integer categoryId , String state);
}
