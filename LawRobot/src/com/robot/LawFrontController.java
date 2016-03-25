package com.robot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.entity.Answer;

/**
 *
 *LawFrontController.java
 */
@Controller
@RequestMapping("/front")
public class LawFrontController {
	@RequestMapping("/ask")
	public @ResponseBody Answer askQuestion(String question){
		return new Answer(question);
	}
}
