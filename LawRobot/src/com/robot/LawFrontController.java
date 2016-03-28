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
		return new Answer("你好！我是法律小助手，我叫小L！");
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
			//查询时按地区和领域搜索
			question.setQuestion("");
			question.setTime("");
			
		} catch (Exception e) {
			quertKeyWords = quest;
			LogUtil.error(getClass(), new AppExpection("LawFrontController.queryQuestion()","分词失败："+quest));
			question.setQuestion(DBConstants.CHAR_LIKE+quest+DBConstants.CHAR_LIKE);
		}
		//找到查询地区和问题的所有回答
		answersList= answerService.find(question);
		//未知答案时保存问题
		question.setQuestion(quest);
		return queryAnswer(question,answersList,quertKeyWords);
	}
	
	/**
	 * 智能机器人算法实现完美，比较庞大，
	 * 小L的定位为法律小助手，主要通过关键字匹配，回答问题，
	 * 实现较为简单的Question&Answering
	 * @param answersList
	 * @param quertKeyWords
	 * @return
	 */
	private Answer queryAnswer(Answer question,List<Answer> answersList, String quertKeyWords) {
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
				if(combine.equalsIgnoreCase(answer.getKeywords())||
						combine.contains(answer.getKeywords())||
						answer.getQuestion().contains(combine)){
					answer.setTime(DateUtil.getNow());
					return answer;
				}
			}
		}
		answerService.saveEntity(new Answer(question.getQuestion(),"","",question.getZone(),question.getFiled()));
		return new Answer(quertKeyWords,"","对不起，我暂不知道相关问题怎么回答，需要继续学习学习！或者尝试选择其他地区或领域咨询！",DateUtil.getNow());
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
