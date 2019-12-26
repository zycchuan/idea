package com.test.filer;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 登录过滤器
 */
@Component
public class LoginFiler extends ZuulFilter {
    @Override
    public String filterType() {
        return PRE_TYPE; //定义什么类型的过滤器
    }
    @Override
    public int filterOrder() {//定义过滤器顺序
        return 4;//过滤器顺序越小越先执行
    }
    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        System.out.println(request.getRequestURI());//   /api/product/product/selectOne
        System.out.println(request.getRequestURL());//   http://localhost:9000/api/product/product/selectOne
        if("/api/product/product/selectOne".equalsIgnoreCase(request.getRequestURI())){
            return true;//为true 就执行  run里面的方法
        }
        return false;//是否生效
    }
    /**
     * 业务逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("拦截了");
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String token = request.getHeader("token");

        if (StringUtils.isBlank(token)){
            token = request.getParameter("token");
        }
        if (StringUtils.isBlank(token)){ //token为null 就 不放行了
           currentContext.setSendZuulResponse(false);
           currentContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
        }
        return null; //放行  返回null 就可以
    }
}
