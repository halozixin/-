package com.zh.crawler;/**
 * @ProjectName: tangshi-analyze
 * @Package: com.zh.crawler
 * @ClassName: Crawler
 * @Author: 97557
 * @Description:
 * @Date: 2019/8/1 20:07
 * @Version: 1.0
 */

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.zh.crawler.common.Page;
import com.zh.crawler.parse.DataParse;
import com.zh.crawler.parse.DocumentParse;
import com.zh.crawler.parse.Parse;
import com.zh.crawler.pipeline.ConsolePipeline;
import com.zh.crawler.pipeline.DatabasePipeline;
import com.zh.crawler.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ZhangHao
 * @create: 2019-08-01 20:07
 **/
public class Crawler {

    /**
     * 未被采集和解析的页面
     */
    private LinkedBlockingQueue<Page> pageQueue = new LinkedBlockingQueue<>();

    /**
     * 放置详情页
     */
    private LinkedBlockingQueue<Page> dataQueue = new LinkedBlockingQueue<>();

    private List<Pipeline> pipelineList = new ArrayList<>();

    private List<Parse> parseList = new ArrayList<>();

    private ExecutorService executorService;

    private WebClient webClient;

    public Crawler(String base, String path) {
        this.initWork();
        this.pageQueue.add(new Page(path, base));
    }

    private void initWork() {

        this.executorService = Executors.newFixedThreadPool(4, new ThreadFactory() {
            final AtomicInteger id = new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"Crawler-THread-"+id.getAndIncrement());
            }
        });
        this.webClient = new WebClient(BrowserVersion.CHROME);
        this.webClient.getOptions().setJavaScriptEnabled(false);
    }
    public Crawler addPipeline(Pipeline pipeline) {
        this.pipelineList.add(pipeline);
        return this;
    }

    public Crawler addParse(Parse parse) {
        this.parseList.add(parse);
        return this;
    }

    private void  parse(){
        while (true){
            try {
             Thread.sleep(1000);
             final Page page = pageQueue.poll();
             if (page == null){
                 continue;
             }
             executorService.submit(()->{
                 try {
                     HtmlPage htmlPage = webClient.getPage(page.getUrl());
                     page.setHtmlPage(htmlPage);
                     Crawler.this.parseList.forEach(parse -> {
                        parse.parse(page);
                        if (page.isDetail()){
                            try {
                                dataQueue.put(page);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else {
                            Iterator<Page> iterator = page.getSubPage().iterator();
                            while (iterator.hasNext()){
                                try {
                                    {
                                        pageQueue.put(iterator.next());
                                        iterator.remove();
                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                     });
                 }catch (Exception r){
                     r.printStackTrace();
                 }
             });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void  pipeline(){
        while (true){
            try {
                Thread.sleep(1000);
                final  Page page = dataQueue.poll();
                if (page == null){
                    continue;
                }
                executorService.submit(()->{
                    Crawler.this.pipelineList.forEach(pipeline -> {
                        pipeline.process(page.getDataSet());
                    });
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void start(){
        executorService.execute(this::parse);
        executorService.execute(this::pipeline);
    }

    public void stop(){
        if (executorService != null && !executorService.isShutdown()){
            executorService.shutdown();
        }

    }
    public static void main(String[] args) {
        Crawler crawler = new Crawler("http://so.gushiwen.org","/gushi/tangshi.aspx");
        crawler.addParse(new DocumentParse());
        crawler.addParse(new DataParse());
        crawler.addPipeline(new ConsolePipeline());
        crawler.addPipeline(new DatabasePipeline());
        crawler.start();
    }
}
