package com.zh.crawler.common;/**
 * @ProjectName: tangshi-analyze
 * @Package: com.zh.crawler.common
 * @ClassName: Page
 * @Author: 97557
 * @Description:
 * @Date: 2019/8/1 20:10
 * @Version: 1.0
 */

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ZhangHao
 * @create: 2019-08-01 20:10
 **/
@Getter
@Setter
public class Page {

    private final String path;

    private final  String base;

    private HtmlPage htmlPage;

    private boolean detail;

    private Set<Page> subPage = new HashSet<>();

    private DataSet dataSet = new DataSet();

    public Page(String path,String base){
        this.path = !path.startsWith("/")?"/" +path :path;

        this.base = base.endsWith("/")?base.substring(0,base.length()-1):base;
    }

    public void  put(Page page){
        this.subPage.add(page);
    }
    public String getUrl(){
        return this.getBase()+this.getPath();
    }

}
