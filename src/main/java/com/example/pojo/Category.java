package com.example.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {
    @NotNull(groups = { Update.class })
    private Integer id;
    @NotEmpty(groups = { Update.class , Add.class })
    private String categoryName;
    @NotEmpty(groups = { Update.class , Add.class })
    private String categoryAlias;
    private Integer createUser;
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime updateTime;

    //如果 add update extends Default 就不用寫 group
    public interface Add {}

    public interface Update{}
}
