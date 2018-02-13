package com.gunn.jys.service.impl;

import com.gunn.jys.base.impl.BaseServiceImpl;
import com.gunn.jys.constant.user.UserConst;
import com.gunn.jys.entity.Resource;
import com.gunn.jys.entity.User;
import com.gunn.jys.mapper.ResourceMapper;
import com.gunn.jys.mapper.UserMapper;
import com.gunn.jys.service.ResourceService;
import com.gunn.jys.vo.user.UserResourceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl extends BaseServiceImpl<ResourceMapper,Resource> implements ResourceService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<UserResourceVo> getByUserId(Integer userId) {
        if (null == userId) {
            return null;
        }
        User user = userMapper.selectByPrimaryKey(userId);
        List<UserResourceVo> userResourceVos;
        if (UserConst.IsAdmin.IS_ADMIN == user.getIsAdmin()) {
            userResourceVos = dao.findAll();
        } else {
            userResourceVos = dao.findByUserId(userId);
        }
        return userResourceVos;
    }
}
