package com.robot.entity;

import com.sys.base.BaseEntity;

/**
 *
 *Answer.java
 */
public class Answer extends BaseEntity{
	//����ؼ���
	public String keywords;
	//�����
	public String answer;
	
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
	
	
}
