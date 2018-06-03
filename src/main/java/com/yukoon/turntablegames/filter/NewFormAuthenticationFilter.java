package com.yukoon.turntablegames.filter;

import com.yukoon.turntablegames.services.UserService;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.List;

public class NewFormAuthenticationFilter extends FormAuthenticationFilter {
	@Autowired
	private UserService userService;

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
		Session session  = subject.getSession();
		Integer id = Integer.parseInt((String)subject.getPrincipal());
		return super.onLoginSuccess(token, subject, request, response);
	}
}
