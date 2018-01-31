package com.gunn.jys.mapper;

import com.gunn.jys.base.BaseMapper;
import com.gunn.jys.entity.User;

public interface UserMapper extends BaseMapper<User>{

    User selectByUsername(String username);

}