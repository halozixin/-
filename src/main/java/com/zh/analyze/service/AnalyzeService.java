package com.zh.analyze.service;

import com.zh.analyze.entity.PoetryInfo;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: tangshi-analyze
 * @Package: com.zh.analyze.service
 * @ClassName: AnalyzeService
 * @Author: 97557
 * @Description:
 * @Date: 2019/8/1 21:12
 * @Version: 1.0
 */
public interface AnalyzeService {
    Map<String, Integer> creationRankingAnalyze();

    Map<String,Integer> cloudWords();


    List<PoetryInfo> queryPoetryInfoByTitle(String title);

    List<PoetryInfo> queryPoetryInfoByAuthor(String author);
}
