package com.github.houbb.checksum.util;

import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassUtil;
import com.github.houbb.heaven.util.lang.reflect.ReflectFieldUtil;
import com.github.houbb.heaven.util.secrect.Md5Util;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
public class CheckValueUtils {

    private CheckValueUtils(){}

    public static String buildCheckValue(Object object) {
        Class<?> clazz = object.getClass();

        // 获取所有字段的 fieldMap
        Map<String, Field> fieldMap = ClassUtil.getAllFieldMap(clazz);

        // 移除 checkValue 名称的
        fieldMap.remove("checkValue");

        // 对字段按名称排序
        Set<String> fieldNameSet = fieldMap.keySet();
        List<String> fieldNameList = new ArrayList<>(fieldNameSet);
        Collections.sort(fieldNameList);

        // 反射获取所有字符串的值
        StringBuilder stringBuilder = new StringBuilder();
        for(String fieldName : fieldNameList) {
            Object value = ReflectFieldUtil.getValue(fieldName, object);
            // 反射获取值
            String valueStr = StringUtil.objectToString(value, "");

            // 拼接
            stringBuilder.append(fieldName).append("=").append(valueStr);
        }


        //md5 加签
        return Md5Util.md5(stringBuilder.toString());
    }
}
