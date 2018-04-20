package com.gunn.jys.service;

import com.gunn.jys.base.BaseService;
import com.gunn.jys.entity.Permis;
import com.gunn.jys.vo.node.Node;
import com.gunn.jys.vo.permis.PermisNodeVo;

import java.util.List;

public interface PermisService extends BaseService<Permis> {

    PermisNodeVo findPermisByUserId(Integer userId);

    int updatePermis(Integer userId, List<Integer> permisIds);
}
