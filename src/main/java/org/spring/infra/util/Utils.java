package org.spring.infra.util;

import com.alibaba.fastjson.JSON;

/**
 * @author jianglong.li
 * @date 2021-06-12 15:18
 **/
public class Utils {
    private Utils() {}

    public static  <T> String parseObject2Json(T data) {
        return JSON.toJSONString(data);
    }
}
