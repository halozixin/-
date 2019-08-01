package com.zh.crawler.pipeline;/**
 * @ProjectName: tangshi-analyze
 * @Package: com.zh.crawler.pipeline
 * @ClassName: Pipeline
 * @Author: 97557
 * @Description:
 * @Date: 2019/8/1 20:18
 * @Version: 1.0
 */

import com.zh.crawler.common.DataSet;

/**
 * @author ZhangHao
 * @create: 2019-08-01 20:18
 **/
public interface Pipeline {
    void process(DataSet dataSet);

}
