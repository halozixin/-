package com.zh.crawler.parse;/**
 * @ProjectName: tangshi-analyze
 * @Package: com.zh.crawler.parse
 * @ClassName: DataParse
 * @Author: 97557
 * @Description:
 * @Date: 2019/8/1 20:34
 * @Version: 1.0
 */

import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.zh.crawler.common.Page;

/**
 * @author ZhangHao
 * @create: 2019-08-01 20:34
 **/
public class DataParse implements Parse{

    /**
     * /html/body/div[3]/div[1]/div[2]/div[1]/p/a[1]
     */
    private String dynastyXpath = "//div[@class='cont']/p[@class='source'][1]/a[1]/text()";
    private String authorXpath = "//div[@class='cont']/p[@class='source'][1]/a[2]/text()";
    private String titleXpath = "//div[@class='cont']/h1/text()";
    private String contentXpath = "//div[@class='cont']/div[@class='contson']";

    @Override
    public void parse(Page page) {
        if (!page.isDetail()){
            return;
        }
        try{
            HtmlPage htmlPage = page.getHtmlPage();
            HtmlElement body = htmlPage.getBody();
            String dynasty = ((DomText)body.getByXPath(dynastyXpath).get(0)).asText();
            String author = ((DomText) body.getByXPath(authorXpath).get(0)).asText();
            String title = ((DomText) body.getByXPath(titleXpath).get(0)).asText();
            HtmlDivision division = (HtmlDivision) body.getByXPath(contentXpath).get(0);
            String content = division.getTextContent();
            page.getDataSet().putData("dynasty",dynasty);
            page.getDataSet().putData("author", author);
            page.getDataSet().putData("title", title);
            page.getDataSet().putData("content", content);
            page.getDataSet().putData("url", page.getUrl());

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
