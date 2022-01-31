package com.lightr.lblog.controller;


import com.lightr.lblog.model.dto.AccountDto;
import com.lightr.lblog.model.entity.BlogUser;
import com.lightr.lblog.model.vo.AccountVo;
import com.lightr.lblog.util.JwtUtil;
import com.lightr.lblog.util.Result;
import com.lightr.lblog.util.SqlUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Lightr
 * @since 2022-01-18
 */
@RestController
@RequestMapping("/user")
public class BlogUserController {

    private SqlUtil sqlUtil;

    @Autowired
    public void setSqlUtil(SqlUtil sqlUtil) {
        this.sqlUtil = sqlUtil;
    }


    @RequestMapping("/login")
    public Result login(@Validated AccountVo accountVo, HttpServletResponse response) throws Exception {
        String name = accountVo.getName();
        String password = accountVo.getPassword();
        if (sqlUtil.verifyUser(name, password)) {
            BlogUser user = sqlUtil.getUser(name);
            String token = JwtUtil.getJwtToken(user.getId(), user.getSalt());
            response.setHeader("Authorization", token);
            response.setHeader("Access-Control-Expose-Headers", "Authorization");
            AccountDto accountDto = new AccountDto();
            accountDto.setName(name);
            accountDto.setToken(token);
            accountDto.setUserId(user.getId());
            return Result.Successful(200, "登陆成功!", accountDto);
        }
        throw new IncorrectCredentialsException("登录失败！用户名或密码错误!");
    }

    @RequestMapping("/logout")
    @RequiresAuthentication
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.Successful(200, "退出成功!", null);
    }

    @RequestMapping("/check")
    public Result checkTk() {
        return Result.Successful(null);
    }

}
