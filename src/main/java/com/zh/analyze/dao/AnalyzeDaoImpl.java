package com.zh.analyze.dao;/**
 * @ProjectName: tangshi-analyze
 * @Package: com.zh.analyze.dao
 * @ClassName: AnalyzeDaoImpl
 * @Author: 97557
 * @Description:
 * @Date: 2019/8/1 21:10
 * @Version: 1.0
 */

import com.zh.analyze.entity.PoetryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author ZhangHao
 * @create: 2019-08-01 21:10
 **/
public class AnalyzeDaoImpl implements AnalyzeDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<PoetryInfo> loadAll() {
        String sql = "select meta_id, meta_url, meta_create,author_name, author_dynasty, content_title, content_body from poetry_info";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            PoetryInfo poetryInfo = new PoetryInfo();
            poetryInfo.setMetaId(resultSet.getString("meta_id"));
            poetryInfo.setMetaUrl(resultSet.getString("meta_url"));
            poetryInfo.setMetaCreate(resultSet.getTimestamp("meta_create").toLocalDateTime());
            poetryInfo.setAuthorName(resultSet.getString("author_name"));
            poetryInfo.setAuthorDynasty(resultSet.getString("author_dynasty"));
            poetryInfo.setContentTitle(resultSet.getString("content_title"));
            poetryInfo.setContentBody(resultSet.getString("content_body"));
            return poetryInfo;
        });
    }
}
