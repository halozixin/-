package com.zh.crawler.common;/**
 * @ProjectName: tangshi-analyze
 * @Package: com.zh.crawler.common
 * @ClassName: DataSet
 * @Author: 97557
 * @Description:
 * @Date: 2019/8/1 20:07
 * @Version: 1.0
 */

import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangHao
 * @create: 2019-08-01 20:07
 **/
@ToString
public class DataSet {
    private Map<String,Object> data = new HashMap<>();

    public Object getData(String key){
        return data.get(key);
    }

    public void putData(String key,Object value){
        data.put(key,value);
    }


    public Map<String,Object> getData(){
        return new HashMap<>(data);
    }



}
