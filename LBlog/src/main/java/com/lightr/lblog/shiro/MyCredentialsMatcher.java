package com.lightr.lblog.shiro;

import com.lightr.lblog.util.JwtUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;


public class MyCredentialsMatcher implements CredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {

        //两个参数都是一样的,第一个是原始的token,第二个是我们return过来的
        // 用 return的验证下就可以了
        String[] tokens = (String[]) authenticationInfo.getCredentials();//jwt对象
        String token = tokens[0];
        String salt = tokens[1];
        return JwtUtil.VerifyToken(token, salt);
    }
}
