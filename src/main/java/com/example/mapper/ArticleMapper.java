package com.example.mapper;

import com.example.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {

    //insert
    @Insert("insert into article (title ,content ,cover_img ,state ,category_id ,create_user ,create_time ,update_time)" +
            "values (#{title} ,#{content} ,#{coverImg} ,#{categoryId} ,#{createUser} ,#{createTime} ,#{updateTime})")
    void add (Article article);

    //List<Article> list (Integer pageNum ,Integer pageSize ,Integer categoryId , String state);
    List<Article> list (Integer userId  ,Integer categoryId , String state);
}
