package com.sys.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sys.SysConstants;
import com.sys.base.BaseController;
import com.sys.common.AppExpection;
import com.sys.common.util.SessionUtil;
import com.sys.common.util.StringUtil;
import com.sys.db.entity.User;
import com.sys.db.service.UserService;

/**
 * ��¼
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	private static final String LOGINPAGE = "bak/robotlogin";
	private static final String REGISTPAGE = "login/regist";
	private static final String TO_INDEX = "redirect:/admin.do";
	@Resource
	UserService userService;

	@RequestMapping(value = "")
	public String tologin(HttpSession session) throws InterruptedException {
		if (null != SessionUtil.sysUser(session)) {
			return TO_INDEX;
		}
		return LOGINPAGE;
	}


	@RequestMapping(value = "verify")
	public String verifyLogin(Model m, HttpSession session, String userName,
			String password, String code) {
		if (StringUtil.isNull(userName) || StringUtil.isNull(password)) {
			return forwordExpPage(m, new AppExpection(
					"LoginController.verifyLogin(User, Model, HttpSession)",
					"�û���������Ϊ�գ�"), LOGINPAGE);
		}
		userName = userName.trim();
		password = password.trim();
		User loginUser = userService.userVerify(new User(userName, password));
		if (loginUser == null) {
			return forwordExpPage(m, new AppExpection(
					"LoginController.verifyLogin(User, Model, HttpSession)",
					"�û�������������δע�ᣡ"), LOGINPAGE);
		}

		// ��¼��֤
		session.setAttribute(SysConstants.SYSUSER, loginUser);
		return TO_INDEX;
	}


	@RequestMapping(value = "regist")
	public String registUser(User user, Model m, String code,
			HttpSession session) {
		if (user == null || StringUtil.isNull(user.getUserName())
				|| StringUtil.isNull(user.getPassword())) {
			return forwordExpPage(m,
					new AppExpection("LoginController.registUser(User,Model)",
							"ע���û�����Ϊ�գ��������룡"), REGISTPAGE);
		}
		boolean result = userService.userRegist(user) != null;
		if (!result) {
			return forwordExpPage(m, new AppExpection(
					"LoginController.registUser(User,Model)",
					"ע�����û������쳣������ϵ����Ա��"), REGISTPAGE);
		}
		return LOGINPAGE;
	}

	@RequestMapping(value = "logout")
	public void logOut(HttpSession session, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		if (SessionUtil.sysUser(session) != null) {
			session.invalidate();
		}
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ "/";
		response.sendRedirect(basePath);
	}
}
