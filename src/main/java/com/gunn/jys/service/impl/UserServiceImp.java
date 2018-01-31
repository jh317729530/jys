package com.gunn.jys.service.impl;

import com.gunn.jys.base.impl.BaseServiceImpl;
import com.gunn.jys.entity.User;
import com.gunn.jys.mapper.UserMapper;
import com.gunn.jys.service.UserService;
import com.gunn.jys.util.CryptologyUtil;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp extends BaseServiceImpl<UserMapper, User> implements UserService {

    @Override
    public void addUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        String salt = CryptologyUtil.getSalt();
        user.setSalt(salt);
        user.setPassword(CryptologyUtil.shiroMd5(password, salt));
        dao.insert(user);
    }

    @Override
    public User selectByUsername(String username) {
        return dao.selectByUsername(username);
    }
}
