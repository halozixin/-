package com.zh.config;/**
 * @ProjectName: tangshi-analyze
 * @Package: com.zh.config
 * @ClassName: CrawlerProperties
 * @Author: 97557
 * @Description:
 * @Date: 2019/8/1 19:50
 * @Version: 1.0
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ZhangHao
 * @create: 2019-08-01 19:50
 **/
@ConfigurationProperties(prefix = "crawler")
@Component
@Data
public class CrawlerProperties {

    private String base;

    private String path;

    private Integer thread;

}
