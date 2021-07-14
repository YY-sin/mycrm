package cn.xaut.crm.settings.dao;

import cn.xaut.crm.settings.domain.User;

import java.util.Map;

public interface UserDao {

    User login(Map map);
}
