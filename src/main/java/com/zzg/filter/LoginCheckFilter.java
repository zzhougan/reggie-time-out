package com.zzg.filter;


import com.alibaba.fastjson.JSON;
import com.zzg.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Date 2022/11/1
 * @Author: Gan
 * @Description: 检测用户是否登录
 **/
@WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    //定义不需要处理的路径
    private final String[] uris = {
            "/employee/login",
            "/employee/logout",
            "/backend/**",
            "/front/**"
    };
    //路径匹配器，专门用来进行路径比较，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //获取本次请求的URI
        String requestURI = req.getRequestURI();
        //判断本次请求是否需要处理
        //如果不需要处理或者是已登录状态，则直接放行
        //log.info("查询到用户Session:" + req.getSession().getAttribute("emp"));
        if(check(requestURI) || req.getSession().getAttribute("emp") != null){
            filterChain.doFilter(req, resp);
            return ;
        }
        //如果未登录则返回未登录的结果，通过输出流返回
        resp.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return ;
    }

    /**
     * 检测传入的URI是否在放行列表中
     * @param requestURI 所要检测的URI
     * @return true表示在
     */
    private boolean check(String requestURI){
        for (String s : uris) {
            boolean match = PATH_MATCHER.match(s, requestURI);
            if(match){
                return true;
            }
        }
        return false;
    }
}
