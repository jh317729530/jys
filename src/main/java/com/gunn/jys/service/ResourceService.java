package com.gunn.jys.service;

import com.gunn.jys.base.BaseService;
import com.gunn.jys.entity.Resource;
import com.gunn.jys.vo.user.UserResourceVo;

import java.util.List;

public interface ResourceService extends BaseService<Resource> {

    List<UserResourceVo> getByUserId(Integer userId);
}
