package com.robot;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.db.Answer;
import com.robot.db.AnswerService;
import com.sys.SysConstants;
import com.sys.base.dto.PageResult;
import com.sys.base.dto.QueryParam;
import com.sys.common.AppExpection;
import com.sys.common.util.StringUtil;
import com.sys.db.DBConstants;

/**
 *
 *LawFrontController.java
 */
@Controller
@RequestMapping("/admin")
public class LawAdminController {
	private static final String LAWROBOT_BAK="bak/";
	private static final String LAWROBOT_VIEW="bak/content/";
	
	@Resource
	AnswerService answerService;
	
	@RequestMapping
	public String frontPage(String view) throws InterruptedException{
		if(StringUtil.isNull(view)){
			view="robotadmin";
			return LAWROBOT_BAK+view;
		}
		return LAWROBOT_VIEW+view;
	}
	/**
	 * 问题答案列表
	 * 
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "questPage")
	public @ResponseBody PageResult<Answer> AnswerListPage(QueryParam<Answer> params,
			Model m, Answer answer) {
		if(answer!=null&&StringUtil.isNotNull(answer.getZone())){
			answer.setZone(DBConstants.CHAR_LIKE+answer.getZone()+DBConstants.CHAR_LIKE);
		}
		if(answer!=null&&StringUtil.isNotNull(answer.getFiled())){
			answer.setFiled(DBConstants.CHAR_LIKE+answer.getFiled()+DBConstants.CHAR_LIKE);
		}
		if(answer!=null&&StringUtil.isNotNull(answer.getKeywords())){
			answer.setKeywords(DBConstants.CHAR_LIKE+answer.getKeywords()+DBConstants.CHAR_LIKE);
		}
		params.setParam(answer);
		PageResult<Answer> result = answerService.pageQuery(params);
		return result;
	}

	/**
	 * 问题答案详情
	 * 
	 * @param id
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "questForm")
	public String AanswerForm(Integer id, Model m) {
		Answer answer = answerService.findById(id);
		m.addAttribute("answer", answer);
		return SysConstants.FORM_SPACE+"questForm";
	}


	/**
	 * 问题答案修改
	 * 
	 * @param answer
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "questModify")
	public @ResponseBody Answer answerModify(Answer answer, HttpSession session)
			throws AppExpection {
		if(answer!=null&&answer.getId()==null){
			answerService.saveEntity(answer);
		}else{
			answerService.updateEntity(answer);
		}
		
		return answer;
	}

	/**
	 * 问题答案删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "questDelete")
	public @ResponseBody Answer answerDelete(Integer id) {
		Answer answer = answerService.findById(id);
		answerService.deleteEntity(answer);
		return answer;
	}
}
