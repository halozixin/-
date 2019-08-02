package com.zh.analyze.service;/**
 * @ProjectName: tangshi-analyze
 * @Package: com.zh.analyze.service
 * @ClassName: AnalyzeServiceImpl
 * @Author: 97557
 * @Description:
 * @Date: 2019/8/1 21:15
 * @Version: 1.0
 */

import com.zh.analyze.dao.AnalyzeDao;
import com.zh.analyze.entity.PoetryInfo;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author ZhangHao
 * @create: 2019-08-01 21:15
 **/
@Service
public class AnalyzeServiceImpl implements AnalyzeService{
  @Autowired
  private AnalyzeDao analyzeDao;

    @Override
    public Map<String, Integer> creationRankingAnalyze() {
        Map<String,Integer> summary = new HashMap<>();
        analyzeDao.loadAll().stream().collect(Collectors.groupingBy(PoetryInfo::getAuthorName))
                .forEach((authorName,poetryInfoList)->summary.put(authorName,poetryInfoList.size()));
        return summary;
    }



    @Override
    public Map<String, Integer> cloudWords() {
        Map<String,Integer> map = new ConcurrentHashMap<>();
        analyzeDao.loadAll().parallelStream()
                .forEach(poetryInfo -> {
                    List<Term> terms = new ArrayList<>();
                    terms.addAll(NlpAnalysis.parse(poetryInfo.getContentTitle()).getTerms());
                    terms.addAll(NlpAnalysis.parse(poetryInfo.getContentBody()).getTerms());
                    terms.stream()
                            .filter(t ->!t.getNatureStr().equals("w")&&!t.getNatureStr().equals("null"))
                            .filter(t -> t.getRealName().length() >= 2)
                            .collect(Collectors.groupingBy(Term::getRealName))
                            .forEach((k,v)->
                                    map.compute(k,(s,c)->c == null? 0:c+1));
                });

        return map;
    }

    @Override
    public List<PoetryInfo> queryPoetryInfoByTitle(String title) {
        return analyzeDao.loadAll().stream().filter(poetryInfo -> poetryInfo.getContentTitle().contains(title)).collect(Collectors.toList());
    }

    @Override
    public List<PoetryInfo> queryPoetryInfoByAuthor(String author) {
        return analyzeDao.loadAll().stream().filter(poetryInfo -> poetryInfo.getAuthorName().equalsIgnoreCase(author)).collect(Collectors.toList());

    }
}
