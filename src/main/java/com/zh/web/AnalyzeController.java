package com.zh.web;/**
 * @ProjectName: tangshi-analyze
 * @Package: com.zh.web
 * @ClassName: AnalyzeController
 * @Author: 97557
 * @Description:
 * @Date: 2019/8/1 21:09
 * @Version: 1.0
 */

import com.zh.analyze.service.AnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ZhangHao
 * @create: 2019-08-01 21:09
 **/
@RestController
@RequestMapping(value = "/analyze")
public class AnalyzeController {

    @Autowired
    private AnalyzeService analyzeService;

    @GetMapping(value = "/creation/ranking")
    public Map<String, Integer> creationRanking(){
        Map<String, Integer> map = analyzeService.creationRankingAnalyze();
        return map;

    }

    @GetMapping(value = "/cloud/words")
    public Map<String, Integer> cloudWords(){
        Map<String, Integer> map = analyzeService.cloudWords();
        return map;
    }

}
