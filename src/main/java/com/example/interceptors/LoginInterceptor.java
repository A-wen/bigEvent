package com.example.interceptors;

import com.example.utils.JwtUitl;
import com.example.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor  implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request,  HttpServletResponse response,  Object handler) throws Exception {
        //取得JWT
        String token = request.getHeader("Authorization");
        //驗證
        try {
            //從redis 中獲取相同的 token
            ValueOperations<String , String> operations = stringRedisTemplate.opsForValue();
            String redisToken = operations.get(token);
            if (redisToken == null) {
                throw new RuntimeException();
            }

            Map<String,Object> cliams =  JwtUitl.verifyToken(token);
            //成功
            return true;
        }catch (Exception e) {

            response.setStatus(401);
            //返回
            return false;

        }
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        //清空
        ThreadLocalUtil.remove();
    }
}
