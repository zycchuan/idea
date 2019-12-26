package com.test.filer;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 *
 * 开发限流拦截器
 */
@Component
public class OrderRateLimitFilter extends ZuulFilter {
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(1000);//每秒产生1000个令牌
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -4;
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

    @Override
    public Object run() throws ZuulException {
        System.out.println("拦截了");
        RequestContext currentContext = RequestContext.getCurrentContext();
        if (!RATE_LIMITER.tryAcquire()){//马上获取令牌 //没有拿到令牌去拦截
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
        }
        return null;
    }
}
