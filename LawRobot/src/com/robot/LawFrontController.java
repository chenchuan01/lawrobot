package com.robot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.SpringContextHolder;
import com.dic.DictionaryManage;
import com.dic.Segmentation;
import com.dic.impl.DictionaryManageImpl;
import com.robot.db.Answer;
import com.robot.db.AnswerService;
import com.robot.dto.Question;
import com.sys.common.AppExpection;
import com.sys.common.util.CommonUtil;
import com.sys.common.util.DateUtil;
import com.sys.common.util.LogUtil;
import com.sys.common.util.StringUtil;
import com.sys.db.DBConstants;

/**
 *LawFrontController.java
 */
@Controller
@RequestMapping("/front")
public class LawFrontController {
	@Resource
	AnswerService answerService;
	
	@RequestMapping
	public String frontPage(Model m){
		return "front/robotfront";
	}
	@RequestMapping("/hello")
	public @ResponseBody Answer hello() throws InterruptedException{
		Thread.sleep(800);
		return new Answer("��ã����Ƿ���С���֣��ҽ�СL��");
	}
	@RequestMapping("/ask")
	public @ResponseBody Question askQuestion(Question question) throws InterruptedException{
		Thread.sleep(800);
		question.setTime(DateUtil.getNow());
		return question;
	}
	@RequestMapping("/query")
	public @ResponseBody Answer queryQuestion(Answer question) throws AppExpection, InterruptedException{
		Thread.sleep(500);
		String quest = question.getQuestion();
		DictionaryManage dictionaryManage = (DictionaryManageImpl)SpringContextHolder.getBean("dictionaryManage");
		Segmentation dicSeg= dictionaryManage.getSegmentation();
		String quertKeyWords="";
		List<Answer> answersList = new ArrayList<Answer>();
		try {
			quertKeyWords = dicSeg.Bmm(quest);
			//��ѯʱ����������������
			question.setQuestion("");
			question.setTime("");
			
		} catch (Exception e) {
			quertKeyWords = quest;
			LogUtil.error(getClass(), new AppExpection("LawFrontController.queryQuestion()","�ִ�ʧ�ܣ�"+quest));
			question.setQuestion(DBConstants.CHAR_LIKE+quest+DBConstants.CHAR_LIKE);
		}
		//�ҵ���ѯ��������������лش�
		answersList= answerService.find(question);
		//δ֪��ʱ��������
		question.setQuestion(quest);
		return queryAnswer(question,answersList,quertKeyWords);
	}
	
	/**
	 * ���ܻ������㷨ʵ���������Ƚ��Ӵ�
	 * СL�Ķ�λΪ����С���֣���Ҫͨ���ؼ���ƥ�䣬�ش����⣬
	 * ʵ�ֽ�Ϊ�򵥵�Question&Answering
	 * @param answersList
	 * @param quertKeyWords
	 * @return
	 */
	private Answer queryAnswer(Answer question,List<Answer> answersList, String quertKeyWords) {
		String[] keyWords = quertKeyWords.split("/");
		/**
		 * �ִʺ�Ĺؼ��֣�
		 * ����������ϣ�
		 * ȥƥ���ѯ���Ĵ𰸹����Ĺؼ���
		 * ��Դ���Ľϴ󣬵�������ʵ��
		 */
		List<String> combineWords = CommonUtil.combineWords(keyWords);
		for (String combine : combineWords) {
			for (Answer answer : answersList) {
				if(combine.equalsIgnoreCase(answer.getKeywords())||
						combine.contains(answer.getKeywords())||
						answer.getQuestion().contains(combine)){
					answer.setTime(DateUtil.getNow());
					return answer;
				}
			}
		}
		answerService.saveEntity(new Answer(question.getQuestion(),"","",question.getZone(),question.getFiled()));
		return new Answer(quertKeyWords,"","�Բ������ݲ�֪�����������ô�ش���Ҫ����ѧϰѧϰ�����߳���ѡ������������������ѯ��",DateUtil.getNow());
	}
	@RequestMapping("/typehead")
	public @ResponseBody List<Answer> typehead(Answer query){
		if(query!=null&&StringUtil.isNotNull(query.getQuestion())){
			
			query.setKeywords(DBConstants.CHAR_LIKE+query.getQuestion()+DBConstants.CHAR_LIKE);
			query.setQuestion("");
			List<Answer> list = answerService.find(query);
			return list;
		}
		return new ArrayList<Answer>();
	}
}
