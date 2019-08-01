package com.zh.crawler.pipeline;/**
 * @ProjectName: tangshi-analyze
 * @Package: com.zh.crawler.pipeline
 * @ClassName: ConsolePipeline
 * @Author: 97557
 * @Description:
 * @Date: 2019/8/1 20:21
 * @Version: 1.0
 */

import com.zh.crawler.common.DataSet;
import org.springframework.stereotype.Component;

/**
 * @author ZhangHao
 * @create: 2019-08-01 20:21
 **/
@Component
public class ConsolePipeline implements Pipeline{

    @Override
    public void process(DataSet dataSet) {
        System.out.println(dataSet.getData());
    }
}
