package com.zh.analyze.dao;

import com.zh.analyze.entity.PoetryInfo;

import java.util.List;

/**
 * @ProjectName: tangshi-analyze
 * @Package: com.zh.analyze.dao
 * @ClassName: AnalyzeDao
 * @Author: 97557
 * @Description:
 * @Date: 2019/8/1 20:16
 * @Version: 1.0
 */
public interface AnalyzeDao {
    List<PoetryInfo> loadAll();


}
