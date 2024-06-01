package com.example.service;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserService {
    //根據名字查詢
    User findByUserName(String username);
    //註冊
    void registerUser(String username ,String password);

    void update(User user);

    void updateAvatar(String avatarUrl);

    void updatePwd(String password);
}
