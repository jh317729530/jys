package com.gunn.jys.mapper;

import com.gunn.jys.base.BaseMapper;
import com.gunn.jys.entity.User;
import com.gunn.jys.vo.user.UserInfo;

public interface UserMapper extends BaseMapper<User>{

    User selectByUsername(String username);

    UserInfo selectUserInfo(Integer id);

}