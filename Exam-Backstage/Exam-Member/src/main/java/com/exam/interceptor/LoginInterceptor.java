package com.exam.interceptor;

import com.exam.util.UserHolderUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (UserHolderUtil.getUser() == null) {
            // 不存在，拦截,返回401状态码
            response.setStatus(401);
            return false;
        }
        return true;
    }

}
