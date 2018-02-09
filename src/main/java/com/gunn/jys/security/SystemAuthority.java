package com.gunn.jys.security;

import com.gunn.jys.constant.shiro.ShiroConst;
import com.gunn.jys.util.PackageUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SystemAuthority {

    public Map<String, String> getAllPermission() {
        Map<String, String> permissionMap = new LinkedHashMap<>();
        List<String> anonUrlList = PackageUtil.PERMISSION_MAP.get(ShiroConst.ANON);

        for (String anonUrl : anonUrlList) {
            permissionMap.put(anonUrl, ShiroConst.ANON);
        }

        return permissionMap;
    }
}
