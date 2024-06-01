package com.example.controller;

import com.example.pojo.Result;
import com.example.pojo.User;
import com.example.service.UserService;
import com.example.utils.JwtUitl;
import com.example.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/register")
    public Result register(@Pattern(regexp = "^\\${5,10}") String username , @Pattern(regexp = "^\\${5,10}") String password){

        User u = userService.findByUserName(username);

            if(u==null){

                userService.registerUser(username,password);
                return Result.success();
            }else{
                return Result.error("已使用");
            }

    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\${5,10}") String username , @Pattern(regexp = "^\\${5,10}") String password){
    //用戶查詢
        User user = userService.findByUserName(username);
    //確認用戶是否存在
    if(user==null){
        return Result.error("用戶錯誤");
    }
     //判斷密碼是否正確
        if(user.getPassword().equals(password)){

            Map<String,Object> caliams = new HashMap<>();
            //取得權限Jwt
            caliams.put("id",user.getId());
            caliams.put("username",user.getUsername());
            String tokenString = JwtUitl.gettoken(caliams);
            //把token存進redis
            ValueOperations<String , String> operations = stringRedisTemplate.opsForValue();
            operations.set(tokenString,tokenString,1, TimeUnit.HOURS);


            return Result.success(tokenString);
        }

        return Result.error("密碼錯誤");
    }

    @GetMapping("/userinfo")
    public Result<User> userinfo(){

        Map<String,Object> map = ThreadLocalUtil.get();
        String username = map.get("username").toString();
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("updateAvatar")
    public Result updateAvatar(@RequestParam @URL String Avatar){
        userService.updateAvatar(Avatar);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> parmas , @RequestHeader("Authorization") String token){

        String NewPwd = parmas.get("NewPwd");
        String OldPwd = parmas.get("OldPwd");
        String rePwd = parmas.get("rePwd");

        if(!StringUtils.hasLength(NewPwd) || !StringUtils.hasLength(OldPwd) || !StringUtils.hasLength(rePwd)){
            return Result.error("參數不足");
        }

        Map<String,Object> map = ThreadLocalUtil.get();
        String username = map.get("username").toString();
        User TheUser = userService.findByUserName(username);
        if (!TheUser.getPassword().equals(OldPwd)){
            return Result.error("原始密碼錯誤");
        }

        if (!NewPwd.equals(rePwd)){
            return Result.error("新密碼不同");
        }

        userService.updatePwd(NewPwd);
        //刪除相對應的token
        ValueOperations<String , String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);

        return Result.success();

    }
}
