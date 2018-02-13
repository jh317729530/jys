package com.gunn.jys.mapper;

import com.gunn.jys.base.BaseMapper;
import com.gunn.jys.entity.Resource;
import com.gunn.jys.vo.user.UserResourceVo;

import java.util.List;

public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     *  查询全部资源
     * @return
     */
    List<UserResourceVo> findAll();

    /**
     *  根据用户id查找资源
     * @return
     */
    List<UserResourceVo> findByUserId(Integer userId);

}