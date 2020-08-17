package com.ph.springBoot.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

@WebFilter(filterName = "requestParamFilter",urlPatterns = "/**")
public class RequestParamFilter implements Filter {
    //日志系统
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestParamFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("—————————————初始化过滤器———————————————");
    }

    @Override
    public void doFilter(ServletRequest Request, ServletResponse Response, FilterChain Chain) throws IOException, ServletException {
        LOGGER.debug("—————————————过滤器———————————————");
        HttpServletRequest httpRequest = (HttpServletRequest) Request;
        HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpRequest){
            @Override
            public String getParameter(String name) {
                String value = httpRequest.getParameter(name);
                if (StringUtils.isNotBlank(value)){
                    return value.replaceAll("fuck","***");
                }
                return super.getParameter(name);
            }

            @Override
            public String[] getParameterValues(String name) {
                String[] values = httpRequest.getParameterValues(name);
                if (values!=null&&values.length>0){
                    for (int i = 0;i<values.length;i++){
                        values[i] = values[i].replaceAll("fuck","***");
                    }
                    return values;
                }
                return super.getParameterValues(name);
            }
        };
        Chain.doFilter(wrapper,Response);
    }

    @Override
    public void destroy() {
        LOGGER.debug("—————————————销毁过滤器———————————————");
    }
}
