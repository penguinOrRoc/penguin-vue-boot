package com.penguin.vueboot.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

public class UserRealm extends AuthorizingRealm {
    //执行授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑~");
        //给资源授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加资源的授权字符串
       // info.addStringPermission("user:unAuth");
        Subject subject = SecurityUtils.getSubject();
        //可以把用户id等信息存进去
        String userid = (String) subject.getPrincipal();
        // 通过用户id获取用户权限
        String getPermissionByUserId = "perms[user:noedit]";
        //存储用户权限
        info.addStringPermission(getPermissionByUserId);
        return info;
    }

    //执行认证逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("听说执行subject.login(token)会到这？");
        //编写shiro判断用户名和密码
        //1.从数据库获取用户名密码
        String userName = "penguin";
        String password = "1qaz2wsx";
        String userid = "userid";
        //2.从token中获取用户名
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        if(!usernamePasswordToken.getUsername().equals(userName)){
            return null;
        }
        //3.判断密码
        return new SimpleAuthenticationInfo(userid,password,"");
    }
}


