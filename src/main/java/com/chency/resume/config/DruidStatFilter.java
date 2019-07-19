package com.chency.resume.config;

import com.alibaba.druid.support.http.WebStatFilter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * 描述:
 *
 * @author chenchaoyun
 * @create 2019-07-19 10:45
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*",
    initParams = {
        @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")//忽略资源
    }
)
public class DruidStatFilter extends WebStatFilter {

}