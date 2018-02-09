package com.gunn.jys.security;

import com.gunn.jys.constant.shiro.ShiroConst;
import com.gunn.jys.util.PackageUtil;
import com.gunn.jys.util.ShiroUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SystemAuthority {

    public Map<String, String> getAllPermission() {
        Map<String, String> permissionMap = new LinkedHashMap<>();
        List<String> anonUrlList = PackageUtil.PERMISSION_MAP.get(ShiroConst.ANON);
        List<String> permsUrlList = PackageUtil.PERMISSION_MAP.get(ShiroConst.PERMS);

        for (String anonUrl : anonUrlList) {
            permissionMap.put(anonUrl, ShiroConst.ANON);
        }

        permsUrlList.stream().filter(url -> !anonUrlList.contains(url))
                .forEach(url -> permissionMap.put(url, "perms[" + ShiroUtil.getStringPermission(url) + "]"));

        return permissionMap;
    }
}
