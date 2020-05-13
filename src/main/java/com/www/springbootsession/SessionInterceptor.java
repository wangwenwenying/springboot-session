package com.www.springbootsession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//就是拦截器
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
            }
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
            }
    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
        //普通路径放行
        if ("/yanzheng".equals(arg0.getRequestURI()) || "/login".equals(arg0.getRequestURI())) {
            return true;}    
        //权限路径拦截
        Object object = arg0.getSession().getAttribute("users");
        if (null == object) {
            arg1.sendRedirect("http://192.168.1.125:8080/CorporationChainServe");
            System.out.println("login");
            return false;}
        return true;
    }
}
