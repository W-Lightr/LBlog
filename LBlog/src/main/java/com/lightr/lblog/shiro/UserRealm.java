package com.lightr.lblog.shiro;

import com.lightr.lblog.util.JwtUtil;
import com.lightr.lblog.util.SqlUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private SqlUtil sqlUtil;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        this.setCredentialsMatcher(new MyCredentialsMatcher()); //使用自己的密码验证器
        String token = (String) authenticationToken.getCredentials();
        //不做啥验证了，直接给自己的密码校验器 校验jwt完整性
        int userId = JwtUtil.getUserId(token);
        String salt = sqlUtil.getSalt(userId);//获得盐值
        String[] tokens = {token, salt};//把token和盐值绑一起发送去验证
        return new SimpleAuthenticationInfo(userId, tokens, this.getName());
    }
}
