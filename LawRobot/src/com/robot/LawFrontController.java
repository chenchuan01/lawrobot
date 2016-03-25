package com.robot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.robot.entity.Answer;

/**
 *
 *LawFrontController.java
 */
@Controller
@RequestMapping("/front")
public class LawFrontController {
	@RequestMapping("/ask")
	public Answer askQuestion(String question){
		return new Answer();
	}
}
