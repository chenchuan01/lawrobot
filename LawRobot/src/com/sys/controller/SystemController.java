package com.sys.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sys.base.BaseController;
import com.sys.base.dto.PageResult;
import com.sys.base.dto.QueryParam;
import com.sys.common.AppExpection;
import com.sys.db.entity.Config;
import com.sys.db.entity.User;
import com.sys.db.service.ConfigService;
import com.sys.db.service.UserService;

/**
 * @author chenchuan
 * @date 2016��1��22�� ϵͳ���ܿ�����
 */
@Controller
@RequestMapping("/sys")
public class SystemController extends BaseController {
	@Resource
	UserService userService;
	@Resource
	ConfigService configService;

	/**
	 * �û��б�
	 * 
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "userList")
	public String userList(Model m) {
		return "sys/userList";
	}

	/**
	 * �û��б�
	 * 
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "userPage")
	public @ResponseBody PageResult<User> userListPage(QueryParam<User> params,
			Model m, User user) {
		params.setParam(user);
		PageResult<User> result = userService.pageQuery(params);
		return result;
	}

	/**
	 * �û�����
	 * 
	 * @param id
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "userForm")
	public String userForm(Integer id, Model m) {
		User user = userService.findById(id);
		m.addAttribute("user", user);
		return "sys/userForm";
	}

	/**
	 * �����޸�
	 * 
	 * @param id
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "pwdModify")
	public String pwdModify(Integer id, Model m) {
		User user = userService.findById(id);
		m.addAttribute("user", user);
		return "sys/pwdModify";
	}

	/**
	 * �û��޸�
	 * 
	 * @param user
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "userModify")
	public @ResponseBody User userModify(User user, HttpSession session)
			throws AppExpection {
		userService.userUpdate(user);
		return user;
	}

	/**
	 * �û�ɾ��
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "userDelete")
	public String userDelete(Integer id) {
		User user = userService.findById(id);
		userService.deleteEntity(user);
		return "redirect:/sys/userList.do";
	}

	/**
	 * ϵͳ�����б�
	 * 
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "configList")
	public String configList(Model m) throws AppExpection {
		return "sys/configList";
	}

	/**
	 * ϵͳ���÷�ҳ��ѯ
	 * 
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "configPage")
	public @ResponseBody PageResult<Config> configListPage(
			QueryParam<Config> params, Model m, Config config)
			throws AppExpection {
		params.setParam(config);
		PageResult<Config> result = configService.pageQuery(params);
		return result;
	}

	/**
	 * ϵͳ���ñ�
	 * 
	 * @param id
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "configForm")
	public String configForm(Integer id, Model m) throws AppExpection {
		Config config = configService.findById(id);
		m.addAttribute("config", config);
		return "sys/configForm";
	}

	/**
	 * ϵͳ�����޸�
	 * 
	 * @param config
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "configModify")
	public @ResponseBody Config configModify(Config config, HttpSession session)
			throws AppExpection {
		configService.updateEntity(config);
		return config;
	}

	/**
	 * ��־�б�
	 * 
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "logList")
	public String logList(Model m) {
		return "sys/logList";
	}

	@RequestMapping(value = "invokeComarea")
	public String comarea(Integer peopleId, String saveUrl, Model m) {
		m.addAttribute("peopleId", peopleId);
		m.addAttribute("saveUrl", saveUrl);
		return "common/camera";
	}

}
