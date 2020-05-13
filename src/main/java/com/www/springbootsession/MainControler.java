package com.www.springbootsession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MainControler {
    @UserLoginToken
    @RequestMapping("/index")
    public String showindex(){
        return "index";
        }
    @RequestMapping("/login")
    public String showlogin(User user , HttpServletRequest request) {//只是密码的简单判断，哈哈。当然也可以连数据判断
        if("1234".equals(user.getPassword())){
        request.getSession().setAttribute("users", user.getName());
        request.getSession().setMaxInactiveInterval(10);
        //先添加到session,在跳转
        return "index";
    }else {
        return "login";
    }
         }    
    @RequestMapping("/yanzheng")
    //也可以添加标注@ResponseBody 返回数据给页面（js跳转）
    public String yanzheng(User user , HttpServletRequest request){
        //只是密码的简单判断，哈哈。当然也可以连数据判断
        if("1234".equals(user.getPassword())){
            request.getSession().setAttribute("users", user.getName());
            request.getSession().setMaxInactiveInterval(10);
            //先添加到session,在跳转
            return "index";
        }else {
            return "login";
        }
    }
}
