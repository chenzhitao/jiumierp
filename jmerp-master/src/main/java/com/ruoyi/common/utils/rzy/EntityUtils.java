package com.ruoyi.common.utils.rzy;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.formula.functions.T;

import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName EntityUtils
 * @Deacription TODO
 * @Author 方舟
 * @Date 2021/4/14 12:12:57
 * @Version 1.0
 **/
public class EntityUtils {

    /**
     * 清空对象中的空字符串,转为null
     * @Author 方舟
     * @Date 2021/4/14 12:13:57
    **/
    public static Object nullStringToNull(Object obj){
        Class<?> clazz = obj.getClass();
        String str = JSONObject.toJSONString(obj);
        Map<String, Object> stringObjectMap = JSONObject.parseObject(str, Map.class);
        Iterator<Map.Entry<String, Object>> it = stringObjectMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            if(null != entry.getValue() && "".equals(entry.getValue())){
                it.remove();
            }
        }
        return JSONObject.parseObject(JSONObject.toJSONString(stringObjectMap), clazz);
    }


}
