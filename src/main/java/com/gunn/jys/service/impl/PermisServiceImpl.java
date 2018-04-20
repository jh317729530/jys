package com.gunn.jys.service.impl;

import com.gunn.jys.base.impl.BaseServiceImpl;
import com.gunn.jys.entity.Permis;
import com.gunn.jys.mapper.PermisMapper;
import com.gunn.jys.mapper.UserPermisMapper;
import com.gunn.jys.service.PermisService;
import com.gunn.jys.vo.node.DisabledNode;
import com.gunn.jys.vo.node.Node;
import com.gunn.jys.vo.node.PermisVoNode;
import com.gunn.jys.vo.permis.PermisNodeVo;
import com.gunn.jys.vo.permis.PermisVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermisServiceImpl extends BaseServiceImpl<PermisMapper,Permis> implements PermisService {

    @Resource
    private UserPermisMapper userPermisMapper;

    @Override
    public PermisNodeVo findPermisByUserId(Integer userId) {
        List<PermisVo> permisVos = userPermisMapper.findPermisVoByUserId(userId);
        DisabledNode root = new DisabledNode("全部");

        List<PermisVo> parentPermisList = permisVos.stream().filter(permisVo -> permisVo.getParentId().equals(0)).collect(Collectors.toList());
        List<PermisVoNode> parentPermisNodeList = new ArrayList<>();
        parentPermisList.forEach(permisVo -> {
            parentPermisNodeList.add(new PermisVoNode(permisVo.getName(), permisVo.getId(), permisVo.getName(), permisVo.getHasPermis()));
        });

        parentPermisNodeList.forEach(permisVoNode -> {
            List<PermisVo> subPermisVoList = permisVos.stream().filter(permisVo -> permisVo.getParentId().equals(permisVoNode.getPermisId())).collect(Collectors.toList());
            List<PermisVoNode> subPermisVoNodeList = new ArrayList<>();
            subPermisVoList.forEach(permisVo -> {
                subPermisVoNodeList.add(new PermisVoNode(permisVo.getName(), permisVo.getId(), permisVo.getName(), permisVo.getHasPermis()));
            });
            permisVoNode.setChildren(subPermisVoNodeList);
        });

        root.setDisabledChildren(parentPermisNodeList);

        List<Integer> checkPermisIds = permisVos.stream().filter(permisVo -> permisVo.getHasPermis().equals(1)).map(permisVo -> permisVo.getId()).collect(Collectors.toList());

        PermisNodeVo permisNodeVo = new PermisNodeVo();
        permisNodeVo.setRoot(root);
        permisNodeVo.setCheckPermisIds(checkPermisIds);
        return permisNodeVo;
    }
}
