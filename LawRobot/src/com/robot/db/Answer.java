package com.robot.db;

import com.sys.base.BaseEntity;
import com.sys.common.util.DateUtil;

/**
 *Answer.java
 */
public class Answer extends BaseEntity{
	//地区
	private String zone;
	//领域
	private String filed;
	//补全问题
	private String question;
	//问题关键字
	private String keywords;
	//问题答案
	private String answer;
	
	private String time;
	
	public Answer() {
		
	}
	
	public Answer(String question, String keywords, String answer, String time) {
		super();
		this.question = question;
		this.keywords = keywords;
		this.answer = answer;
		this.time = time;
	}
	
	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getFiled() {
		return filed;
	}

	public void setFiled(String filed) {
		this.filed = filed;
	}

	public Answer(String hello) {
		this.answer = hello;
		this.time = DateUtil.getNow();
	}

	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
