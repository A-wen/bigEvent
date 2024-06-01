package com.example.pojo;

import com.example.anno.State;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
public class Article {
    private Integer id ;
    @NotEmpty//不可以null & 空字串
    @Pattern(regexp = "^\\${1,10}")
    private String title ;
    @NotEmpty
    private String content ;
    @NotEmpty
    @URL
    private String coverImg ;
    @State
    private String state ;
    @NotNull
    public Integer categoryId ;
    public Integer createUser;
    private LocalDateTime createTine ;
    private LocalDateTime updateTine ;
}
