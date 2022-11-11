package com.zzg.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2022/11/1
 * @Author: Gan
 * @Description: 将处理好的信息进行包装，以统一格式发送到前端
 **/
@Data
public class R<T> {
    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    private Map<Object, Object> map = new HashMap<>(); //动态数据

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> R<T> error(String msg) {
        R<T> r = new R<>();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}
