package com.zh.crawler.parse;/**
 * @ProjectName: tangshi-analyze
 * @Package: com.zh.crawler.parse
 * @ClassName: DocumentParse
 * @Author: 97557
 * @Description:
 * @Date: 2019/8/1 20:45
 * @Version: 1.0
 */

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.zh.crawler.common.Page;

/**
 * @author ZhangHao
 * @create: 2019-08-01 20:45
 **/
public class DocumentParse implements Parse{
    @Override
    public void parse(Page page) {
        if (page.isDetail()){
            return;
        }
        HtmlPage htmlPage = page.getHtmlPage();
        htmlPage.getBody()
                .getElementsByAttribute("div","class",
                       "typecont" )
                .forEach(htmlElement -> {
                    htmlElement.getElementsByTagName("a")
                            .forEach(e ->{
                                String path =  e.getAttribute("href");
                                Page subPage = new Page(path,page.getBase());
                                subPage.setDetail(true);
                                page.put(subPage);
                            });
                });
    }
}
