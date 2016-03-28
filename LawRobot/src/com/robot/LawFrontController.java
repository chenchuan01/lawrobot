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
		Thread.sleep(1000);
		return new Answer("你好！我是法律小助手，我叫小L！");
	}
	@RequestMapping("/ask")
	public @ResponseBody Question askQuestion(Question question) throws InterruptedException{
		Thread.sleep(100);
		question.setTime(DateUtil.getNow());
		return question;
	}
	@RequestMapping("/query")
	public @ResponseBody Answer queryQuestion(Answer question) throws AppExpection{
		String quest = question.getQuestion();
		DictionaryManage dictionaryManage = (DictionaryManageImpl)SpringContextHolder.getBean("dictionaryManage");
		Segmentation dicSeg= dictionaryManage.getSegmentation();
		String quertKeyWords = dicSeg.Fmm(quest);
		question.setQuestion("");
		question.setTime("");
		//找到查询地区和问题的所有回答
		List<Answer> answersList = answerService.find(question);
		return queryAnswer(answersList,quertKeyWords);
	}
	
	/**
	 * 智能机器人算法实现完美，比较庞大，
	 * 小L的定位为法律小助手，主要通过关键字匹配，回答问题，
	 * 实现较为简单的Question&Answering
	 * @param answersList
	 * @param quertKeyWords
	 * @return
	 */
	private Answer queryAnswer(List<Answer> answersList, String quertKeyWords) {
		String[] keyWords = quertKeyWords.split("/");
		/**
		 * 分词后的关键字，
		 * 依次排列组合，
		 * 去匹配查询出的答案关联的关键字
		 * 资源消耗较大，但功能能实现
		 */
		List<String> combineWords = CommonUtil.combineWords(keyWords);
		for (String combine : combineWords) {
			for (Answer answer : answersList) {
				if(combine.contains(answer.getKeywords())||
						answer.getKeywords().contains(combine)){
					answer.setTime(DateUtil.getNow());
					return answer;
				}
			}
		}
		return new Answer(quertKeyWords,"","对不起，我暂不知道相关问题怎么回答，需要继续学习学习！",DateUtil.getNow());
	}
	@RequestMapping("/typehead")
	public @ResponseBody List<Answer> typehead(Answer query){
		if(query!=null&&StringUtil.isNotNull(query.getQuestion())){
			query.setQuestion(DBConstants.CHAR_LIKE+query.getQuestion()+DBConstants.CHAR_LIKE);
			return answerService.find(query);
		}
		return new ArrayList<Answer>();
	}
}
