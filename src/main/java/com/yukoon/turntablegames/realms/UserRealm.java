package com.yukoon.turntablegames.realms;

import com.yukoon.turntablegames.entities.User;
import com.yukoon.turntablegames.services.RoleService;
import com.yukoon.turntablegames.services.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	//认证的方法
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		//1. 把 AuthenticationToken 转换为 UsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
		//2. 从 UsernamePasswordToken 中获取 username,password构建user
		System.out.println(upToken.getUsername()+upToken.getPassword());
		User user = new User().setUsername(upToken.getUsername()).setPassword(upToken.getPassword().toString());
		//3. 从数据库获取User准备进行比对
		User user_temp = userService.login(user);
		//4. 异常用户抛出异常
		if (user_temp == null) {
			throw new UnknownAccountException("用户不存在!");
		}
		//5. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回.
		Object principal = user_temp.getId();	//这里principal传入ID比较方便
		Object credentials = user_temp.getPassword();
		String realmName = getName();
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,realmName);
		return info;
	}

	//授权方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//1. 从 principalCollection 中来获取登录用户的信息
		Object principal = principalCollection.getPrimaryPrincipal();
		//2. 利用登录的用户的信息来当前用户的角色
		User user_temp = userService.findById(Integer.parseInt((String)principal));
		Set<String> roles = new HashSet<>();
		roles.add(roleService.getRole(user_temp.getRole_id()));
		//3. 创建 SimpleAuthorizationInfo, 并设置其 roles 属性并返回
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		return info;
	}

}
