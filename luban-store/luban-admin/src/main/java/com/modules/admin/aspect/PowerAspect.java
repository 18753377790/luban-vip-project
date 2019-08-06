package com.modules.admin.aspect;

import com.config.RedisKeys;
import com.modules.admin.annotation.Power;
import com.modules.admin.mapper.MenuMapper;
import com.modules.admin.util.RedisUtil;
import com.modules.admin.util.ResponseUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @Author 李非凡
 * @Description:
 * 权限切面类
 * @Date 2019/7/30 12:02
 * @Version 1.0
 */
@Aspect
@Component
public class PowerAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 权限许可
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.modules.admin.annotation.Power)")
    public Object permission(ProceedingJoinPoint point) throws Throwable{
        String requestURI = request.getRequestURI();

        String login_key = request.getHeader(RedisKeys.LOGIN_KEY);
        if (StringUtils.isEmpty(login_key)){
            return ResponseUtil.error("用户未登录");
        }
        Map<String, Object> user = (Map<String, Object>) redisUtil.get(login_key);
        if (user == null){
            return ResponseUtil.error("用户未登录");
        }
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        Power permission = method.getAnnotation(Power.class);
        List<String> acceptUrls = null;
        acceptUrls = menuMapper.selectMenuPower((Integer) user.get("rid"));
        if (acceptUrls != null && acceptUrls.contains(requestURI)){
            return point.proceed(point.getArgs());
        } else {
            return ResponseUtil.error("没有权限");
        }
    }
}
