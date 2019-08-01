package com.zh.analyze.entity;/**
 * @ProjectName: tangshi-analyze
 * @Package: com.zh.analyze.entity
 * @ClassName: PoetryInfo
 * @Author: 97557
 * @Description:
 * @Date: 2019/8/1 20:14
 * @Version: 1.0
 */

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ZhangHao
 * @create: 2019-08-01 20:14
 **/
@Data
public class PoetryInfo {
    /**
     * @Author: ZhangHao
     * @Description 编号
     * @Date 2019/8/1 20:14
     */
    private String metaId;

    /*
    来源地址
     */
    private String metaUrl;


    private LocalDateTime metaCreate;


    private String authorName;


    private String authorDynasty;


    private String contentTitle;

    /**
     * @Author: ZhangHao
     * @Description 正文
     * @Date 2019/8/1 20:16
     */
    private String contentBody;
}
