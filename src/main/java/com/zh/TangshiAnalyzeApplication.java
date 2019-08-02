package com.zh;/**
 * @ProjectName: tangshi-analyze
 * @Package: com.zh
 * @ClassName: TangshiAnalyzeApplication
 * @Author: 97557
 * @Description:
 * @Date: 2019/8/1 19:40
 * @Version: 1.0
 */

import com.zh.config.CrawlerProperties;
import com.zh.crawler.Crawler;
import com.zh.crawler.parse.Parse;
import com.zh.crawler.pipeline.Pipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * @author ZhangHao
 * @create: 2019-08-01 19:40
 **/
@SpringBootApplication
public class TangshiAnalyzeApplication {

    @Autowired
    private CrawlerProperties crawlerProperties;

    @Bean
    @Autowired
    public Crawler crawler(List<Pipeline> pipelineList, List<Parse> parseList) {
        Crawler crawler = new Crawler(crawlerProperties.getBase(), crawlerProperties.getPath());
        pipelineList.forEach(crawler::addPipeline);
        parseList.forEach(crawler::addParse);

        return crawler;
    }
    private static void startCrawler(ApplicationContext context){
        Crawler crawler = context.getBean(Crawler.class);
        crawler.start();
    }


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TangshiAnalyzeApplication.class,args);


    }

}
