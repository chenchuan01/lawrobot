package com.robot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.db.Answer;
import com.robot.dto.Question;
import com.sys.common.util.DateUtil;

/**
 *LawFrontController.java
 */
@Controller
@RequestMapping("/front")
public class LawFrontController {
	
	@RequestMapping
	public String frontPage(Model m){
		return "front/robotfront";
	}
	@RequestMapping("/hello")
	public @ResponseBody Answer hello() throws InterruptedException{
		Thread.sleep(1500);
		return new Answer("��ã����Ƿ���С���֣��ҽ�СL��");
	}
	@RequestMapping("/ask")
	public @ResponseBody Question askQuestion(Question question) throws InterruptedException{
		Thread.sleep(500);
		question.setTime(DateUtil.getNow());
		return question;
	}
	@RequestMapping("/query")
	public @ResponseBody Answer queryQuestion(Question question) throws InterruptedException{
		Thread.sleep(1500);
		return new Answer(question.getContent(), "", "�Բ����һ�û��ѧϰ����", DateUtil.getNow());
	}
	
}
