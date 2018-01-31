package com.gunn.jys.service;

import com.gunn.jys.base.BaseService;
import com.gunn.jys.entity.User;

public interface UserService extends BaseService<User> {

    void addUser(String username, String password);

    User selectByUsername(String username);
}
