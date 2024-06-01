package com.example.pojo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    @NotNull
    private Integer id;
    private String username;
    private String password;

    @NotEmpty//不可以null & 空字串
    @Pattern(regexp = "^\\${1,10}")
    private String nickname;
    @Email
    private String email;
    private String userPic;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
