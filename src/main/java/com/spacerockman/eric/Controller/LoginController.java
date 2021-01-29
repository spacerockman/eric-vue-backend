package com.spacerockman.eric.Controller;

import com.spacerockman.eric.Utils.Response;
import com.spacerockman.eric.pojo.User;
import com.spacerockman.eric.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "/api/login")
    public Response login(@RequestBody User requestUser, HttpSession session){
        //对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
        String password = requestUser.getPassword();
        password = HtmlUtils.htmlEscape(password);
        User user =  userService.get(username,password);
        Response response = new Response();
        if(null != user){
            session.setAttribute("user",user);
            response.setStatus(200);
            response.setOk(true);
            response.setMessage("登陆成功");
        }else {
            response.setStatus(400);
            response.setMessage("用户名或密码错误");
        }
        return response;
    }

}
