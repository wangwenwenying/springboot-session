//package com.www.springbootsession;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTDecodeException;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//
//import com.www.springbootsession.PassToken;
//import com.flkj.ccfcommunity.annotation.UserLoginToken;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Method;
//
//
///**
//* @Description:
//* @Param:
//* @return:
//* @Author: www
//* @Date: 19-12-13
//*/
//public class AuthenticationInterceptor implements HandlerInterceptor {
//    @Autowired
//    UserService userService;
//    @Autowired
//    ManagerMapper managerMapper;
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//    @Override
//    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
//        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
//        // 如果不是映射到方法直接通过
//        if(!(object instanceof HandlerMethod)){
//            return true;
//        }
//        HandlerMethod handlerMethod=(HandlerMethod)object;
//        Method method=handlerMethod.getMethod();
//        //检查是否有passtoken注释，有则跳过认证
//        if (method.isAnnotationPresent(PassToken.class)) {
//            PassToken passToken = method.getAnnotation(PassToken.class);
//            if (passToken.required()) {
//                return true;
//            }
//        }
//        //检查有没有需要用户权限的注解
//        if (method.isAnnotationPresent(UserLoginToken.class)) {
//            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
//            if (userLoginToken.required()) {
//                // 执行认证
//                if (token == null) {
//                    throw new BusinessException("050","无token，请重新登录");
//                }
//                // 获取 token 中的 user id
//                String userId;
//                try {
//                    userId = JWT.decode(token).getAudience().get(0);
//                    System.out.println("................."+token);
//                    System.out.println("============================"+JWT.decode(token).getAudience());
//                    System.out.println(userId);
//                } catch (JWTDecodeException j) {
//                    throw new BusinessException("050","你的登录信息异常");
//                }
//                String tokens = stringRedisTemplate.opsForValue().get(userId);
//                System.out.println(tokens);
//                if (!tokens.equals(token)){
//                    throw new BusinessException("050","你的登录信息异常");
//                }
//                User user = userService.selectByUserOid(userId);
//                if (user == null) {
//                    throw new BusinessException("050","你的登录信息异常");
//                }
//                // 验证 token
//                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getUserPhone())).build();
//                System.out.println(jwtVerifier);
//                try {
//                    jwtVerifier.verify(token);
//                } catch (JWTVerificationException e) {
//                    throw new BusinessException("050","你的登录信息异常");
//                }
//                return true;
//            }
//        }
//        //检查有没有需要用户权限的注解
//        if (method.isAnnotationPresent(ManagerLoginToken.class)) {
//            ManagerLoginToken userLoginToken = method.getAnnotation(ManagerLoginToken.class);
//            if (userLoginToken.required()) {
//                // 执行认证
//                if (token == null) {
//                    throw new BusinessException("050","无token，请重新登录");
//                }
//                // 获取 token 中的 user id
//                String userId;
//                try {
//                    userId = JWT.decode(token).getAudience().get(0);
//                    System.out.println("................."+token);
//                    System.out.println("============================"+userId);
//                } catch (JWTDecodeException j) {
//                    throw new BusinessException("050","你的登录信息异常");
//                }
//                String tokens = stringRedisTemplate.opsForValue().get(userId);
//                System.out.println(tokens);
//                if (!tokens.equals(token)){
//                    throw new BusinessException("050","你的登录信息异常");
//                }
//                Manager user = managerMapper.selectByNumber(userId);
//                if (user == null) {
//                    throw new BusinessException("050","你的登录信息异常");
//                }
//                System.out.println(user.toString());
//                // 验证 token
//                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPhone())).build();
//                try {
//                    jwtVerifier.verify(token);
//                } catch (JWTVerificationException e) {
//                    throw new BusinessException("050","你的登录信息异常");
//                }
//                return true;
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//
//    }
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//
//    }
//}
