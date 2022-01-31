package com.lightr.lblog.config;

import com.lightr.lblog.filter.JwtFilter;
import com.lightr.lblog.shiro.UserRealm;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //1.创建shiroFilter  //负责拦截所有请求
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        //配置系统访问资源
        Map<String, String> map = new HashMap<String, String>();
        map.put("/user/login", "anon");
        map.put("/blogs/article", "anon");
        map.put("/blogs/blog", "anon");
        map.put("/**", "jwt");

        //默认认证界面路径---当认证不通过时跳转
        shiroFilterFactoryBean.setLoginUrl("/");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }

    //创建安全管理器（会自动设置到SecurityUtils中设置这个安全管理器）
    //SecurityUtils可以用来获取subject对象
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("getRealm") UserRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //给安全管理器设置realm
        securityManager.setRealm(realm);

        //关闭shiro的session（无状态的方式使用shiro）
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }

    //创建自定义Realm，注入到spring容器中
    @Bean
    public UserRealm getRealm() {
        UserRealm realm = new UserRealm();
        return realm;
    }

}
