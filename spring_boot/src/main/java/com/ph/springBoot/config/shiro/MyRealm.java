package com.ph.springBoot.config.shiro;

import com.ph.springBoot.modules.account.entity.Resource;
import com.ph.springBoot.modules.account.entity.Role;
import com.ph.springBoot.modules.account.entity.User;
import com.ph.springBoot.modules.account.service.ResourceService;
import com.ph.springBoot.modules.account.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private ResourceService resourceService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        List<Role> roles = user.getRoles();
        if (roles != null && !roles.isEmpty()) {
            roles.stream().forEach(item -> {
                simpleAuthorizationInfo.addRole(item.getRoleName());
                List<Resource> resources =
                        resourceService.getResourcesByRoleId(item.getRoleId());
                if (resources != null && !resources.isEmpty()) {
                    resources.stream().forEach(resource -> {
                        simpleAuthorizationInfo.addStringPermission(resource.getPermission());
                    });
                }
            });
        }

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken Token) throws AuthenticationException {
        String userName = (String)Token.getPrincipal();
        User user = userService.getUserByUserName(userName);
        if (user == null){
            throw new UnknownAccountException("没有此用户");
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
    }
}
