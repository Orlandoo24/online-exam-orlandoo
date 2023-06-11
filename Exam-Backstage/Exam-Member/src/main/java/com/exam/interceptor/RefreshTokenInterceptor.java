package com.exam.interceptor;

import cn.hutool.core.util.StrUtil;
import com.exam.constant.code.HttpCode;
import com.exam.constant.redis.RedisKeyConstant;
import com.exam.exception.BizException;
import com.exam.service.TokenService;
import com.exam.util.UserHolderUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

public class RefreshTokenInterceptor extends HandlerInterceptorAdapter {

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    TokenService tokenService;

    //构造方法初始化 RedisTemplate 实例
    public RefreshTokenInterceptor(RedisTemplate redisTemplate) {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        //先从请求头(K-V)里拿 token
        String token = request.getHeader("token");

        //校验有无 token
        if (StrUtil.isBlank(token)){
            throw new BizException(HttpCode.UN_LOGIN,"请在请求头中添加 token");
        }

        //校验 token 的合法性，防止请求携带不合法的 token
        Boolean success = tokenService.checkToken(token);
        if (!success){
            throw new BizException(HttpCode.UN_LOGIN,"token 不合法");
        }

        //验证 token 是否过期
        String key = RedisKeyConstant.TOKEN_PREFIX + token;
        String redisToken = (String) redisTemplate.opsForValue().get(key);
        if (StrUtil.isBlank(redisToken)){
            throw new BizException(HttpCode.LOGIN_EXPIRED,"token 已经失效，请重新登录");
        }

        //刷新 token 有效期限
        redisTemplate.expire(RedisKeyConstant.TOKEN_PREFIX + token, 3, TimeUnit.HOURS);

        //用户信息保存到线程中
        tokenService.saveToken(token);

        //返回 true 放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {
        UserHolderUtil.remove();
    }

}
