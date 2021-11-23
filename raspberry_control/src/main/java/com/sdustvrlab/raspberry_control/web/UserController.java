package com.sdustvrlab.raspberry_control.web;
import com.sdustvrlab.raspberry_control.dao.UserDao;
import com.sdustvrlab.raspberry_control.bean.Response;
import com.sdustvrlab.raspberry_control.bean.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("/login")
    public Response<Boolean> Login(@RequestParam String username, @RequestParam String password, HttpServletRequest request){
        UserDao userDao = new UserDao();
        Response<Boolean> response = new Response<Boolean>();
        User user = userDao.LoginUser(username,password);
        System.out.println(username + " " +  password);
        if (user!=null){
            response.setResult(true);
            response.setMessage("登录成功！");
            response.setStatus("OK");

            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("username",username);
            request.getSession().setAttribute("password",password);
        }
        else{
            response.setResult(false);
            response.setMessage("登录失败！");
            response.setStatus("ERROR");
        }
        return response;
    }
    @PostMapping("/register")
    public Response<Boolean> Register(@RequestParam String username,@RequestParam String password, @RequestParam String confirm, HttpServletRequest request){

        if(!password.equals(confirm)) {
            return new Response<>("ERROR", "两次输入的密码不相同", false);
        }

        UserDao userDao=new UserDao();
        Response<Boolean> response=new Response<Boolean>();
        userDao.RegisterUser(username,password);
            //if (user!=null){
        response.setResult(true);
        response.setMessage("注册成功！");
        response.setStatus("ok");
            //}
            //else{
            //    response.setResult(false);
            //    response.setMessage("注册失败！");
            //    response.setStatus("ERROR");
            //}
        return response;
    }
}

