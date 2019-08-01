package com.zh.crawler.pipeline;/**
 * @ProjectName: tangshi-analyze
 * @Package: com.zh.crawler.pipeline
 * @ClassName: DatabasePipeline
 * @Author: 97557
 * @Description:
 * @Date: 2019/8/1 20:21
 * @Version: 1.0
 */

import com.zh.analyze.entity.PoetryInfo;
import com.zh.crawler.common.DataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * @author ZhangHao
 * @create: 2019-08-01 20:21
 **/
@Component
public class DatabasePipeline implements Pipeline{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void process(DataSet dataSet) {
        Optional<PoetryInfo> optionalPoetryInfo =convert(dataSet);
        if (optionalPoetryInfo.isPresent()){
            PoetryInfo poetryInfo = optionalPoetryInfo.get();
            String sql = "insert into poetry_info (meta_id, meta_url, meta_create,author_name, author_dynasty, content_title, content_body) values (?,?,?,?,?,?,?)";
            jdbcTemplate.update(sql,
                    poetryInfo.getMetaId(),
                    poetryInfo.getMetaUrl(),
                    poetryInfo.getMetaCreate(),
                    poetryInfo.getAuthorName(),
                    poetryInfo.getAuthorDynasty(),
                    poetryInfo.getContentTitle(),
                    poetryInfo.getContentBody()
            );
        }
    }

    private Optional<PoetryInfo> convert(DataSet dataSet){
        if (dataSet.getData().isEmpty()){
            return Optional.empty();
        }
        else {
            PoetryInfo poetryInfo = new PoetryInfo();
            poetryInfo.setMetaId(UUID.randomUUID().toString().replace("-",""));
            poetryInfo.setMetaUrl((String) dataSet.getData("url"));
            poetryInfo.setMetaCreate(LocalDateTime.now());
            poetryInfo.setAuthorDynasty((String) dataSet.getData("dynasty"));
            poetryInfo.setAuthorName((String) dataSet.getData("author"));
            poetryInfo.setContentTitle((String) dataSet.getData("title"));
            poetryInfo.setContentBody((String) dataSet.getData("content"));
            return Optional.of(poetryInfo);
        }
    }

}
