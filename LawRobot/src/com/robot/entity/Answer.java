package com.robot.entity;

import com.sys.base.BaseEntity;

/**
 *
 *Answer.java
 */
public class Answer extends BaseEntity{
	private String question;
	//问题关键字
	private String keywords;
	//问题答案
	private String answer;
	
	public Answer() {
	}
	public Answer(String question){
		this.question = question;
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
	
	
}
