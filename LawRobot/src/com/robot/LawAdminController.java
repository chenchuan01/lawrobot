package com.robot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 *LawFrontController.java
 */
@Controller
@RequestMapping("/admin")
public class LawAdminController {
	@RequestMapping
	public String frontPage() throws InterruptedException{
		return "bak/robotadmin";
	}
	
}
