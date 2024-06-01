package com.example.service.impl;

import com.example.mapper.ArticleMapper;
import com.example.pojo.Article;
import com.example.pojo.PageBean;
import com.example.service.ArticleService;
import com.example.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper  articleMapper;

    @Override
    public void add(Article article) {
        article.setUpdateTine(LocalDateTime.now());
        article.setCreateTine(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        article.setCreateUser(id);

        articleMapper.add(article);

    }
        @Override
        public PageBean<Article> list(Integer pageNum , Integer pageSize , Integer categoryId , String state){
        //創建 pageBean
        PageBean<Article> pb = new PageBean();

        //開啟分類查詢
        PageHelper.startPage(pageNum,pageSize); ;

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer UserId = (Integer) map.get("id");


        List<Article> as = articleMapper.list(UserId ,categoryId ,state);
        //page中提供方法,可以取得pageHelper方也查詢後,得到總紀錄條數和當前的頁面數據
        Page<Article> p = (Page<Article>) as;

        //把數據塞進pageBean
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
