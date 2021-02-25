package com.penguin.vueboot.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //创建ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        /**
         * 常用过滤器
         * anon   无需认证即可访问
         * authc  必须认证才能访问
         * user   如果使用rememberMe的功能可以直接访问
         * perms  该资源必须授予“资源权限”才能访问
         * role    该资源必须得到“角色权限”才能访问
         */
        //添加Shiro内置过滤器 ，实现权限相关拦截
        Map<String,String> filterMap = new LinkedHashMap<>();
        //放行login
        filterMap.put("/login/**","anon");
        //拦截user
        filterMap.put("/*","authc");
        //授权过滤器，当前授权拦截后，shiro会自动跳转到未授权页面
        filterMap.put("/editUserInfo","perms[user:edit]");  //设置当前资源的授权码
        //修改跳转页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        //设置未授权页面的提示
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    //创建DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建Realm
    @Bean(name = "userRealm") //将方法返回的对象注入容器
    public UserRealm getRealm() {
        return new UserRealm();
    }
}


