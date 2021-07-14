package cn.xaut.crm.settings.service;

import cn.xaut.crm.exception.LoginException;
import cn.xaut.crm.settings.domain.User;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;
}
