package com.gunn.jys.mapper;

import com.gunn.jys.base.BaseMapper;
import com.gunn.jys.entity.Permis;
import com.gunn.jys.entity.UserPermis;
import com.gunn.jys.vo.permis.PermisVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserPermisMapper extends BaseMapper<UserPermis> {

    List<PermisVo> findPermisVoByUserId(@Param("userId") Integer userId);

}