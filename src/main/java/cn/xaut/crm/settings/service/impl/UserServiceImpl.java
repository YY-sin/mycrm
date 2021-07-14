package cn.xaut.crm.settings.service.impl;

import cn.xaut.crm.exception.LoginException;
import cn.xaut.crm.settings.dao.UserDao;
import cn.xaut.crm.settings.domain.User;
import cn.xaut.crm.settings.service.UserService;
import cn.xaut.crm.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User login(String loginAct, String loginPwd, String ip) throws LoginException{
        Map<String,String> map=new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        User user= userDao.login(map);
        //判断账号存在
        if(user==null){

            throw new LoginException("账号或密码错误");

        }
        //验证失效时间
        String expireTime = user.getExpireTime();
        String currentTime = DateTimeUtil.getSysTime();//获取当前时间
        if(expireTime.compareTo(currentTime)<0){//失效时间小于当前时间账号失效

            throw new LoginException("账号已失效");

        }
        //判断锁定状态
        String lockState = user.getLockState();
        if("0".equals(lockState)){

            throw new LoginException("账号已锁定");

        }

        //判断ip地址
        String allowIps = user.getAllowIps();

        if(!allowIps.contains(ip)){//contains判断是否包含

            throw new LoginException("ip地址受限");

        }


        return user;
    }
}
