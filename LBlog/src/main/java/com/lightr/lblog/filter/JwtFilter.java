package com.lightr.lblog.filter;

import com.alibaba.fastjson.JSON;
import com.lightr.lblog.shiro.JwtToken;
import com.lightr.lblog.util.JwtUtil;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends AuthenticatingFilter {
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String token = httpServletRequest.getHeader("Authorization");
        return new JwtToken(token);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String token = httpServletRequest.getHeader("Authorization");
        if (token == null || "".equals(token) || JwtUtil.isExpires(token) || !JwtUtil.isNameExist(token)) {
            HttpServletResponse Resp = (HttpServletResponse) servletResponse;
            Resp.setContentType("application/json;charset=UTF-8");
            Resp.setHeader("Access-Control-Allow-Credentials", "true");
            Resp.setHeader("Access-Control-Allow-Origin", "*");
            Resp.setHeader("Access-Control-Allow-Headers", "Origin, Expires, Content-Type, X-E4M-With, Authorization");
            Resp.getWriter().println(JSON.parse("{msg:\"token失效请重新登录!\",code:401}"));
        }
        return executeLogin(servletRequest, servletResponse);
    }

}
