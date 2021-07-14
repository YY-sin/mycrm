package cn.xaut.crm.settings.web.controller;

import cn.xaut.crm.settings.domain.User;
import cn.xaut.crm.settings.service.impl.UserServiceImpl;
import cn.xaut.crm.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/settings/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    @ResponseBody
    public Object login( HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();
        String loginAct = request.getParameter("loginAct");
        String loginPwd = request.getParameter("loginPwd");
        //将密码的明文形式转换为MD5的密文形式
        loginPwd = MD5Util.getMD5(loginPwd);
        //接收浏览器端的ip地址
        String ip = request.getRemoteAddr();
        System.out.println("--------------ip:"+ip);
        try{
            User user1=userService.login(loginAct,loginPwd,ip);
            map.put("success",false);
            map.put("data",user1);
           request.getSession().setAttribute("user",user1);
        }catch (Exception e){
            map.put("success",false);
            map.put("msg",e.getMessage());
        }
        return map;
    }
}
