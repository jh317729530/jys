package com.gunn.jys.bo;

import java.util.HashMap;
import java.util.Map;

public class MapResult extends InfoResult<Map<String, Object>> {

    @Override
    public Map<String, Object> getInfo() {
        if (null == this.info) {
            Map<String, Object> map = new HashMap<>();
            setInfo(map);
            return info;
        }
        return info;
    }
}
