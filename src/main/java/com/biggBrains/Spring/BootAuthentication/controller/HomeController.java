package com.biggBrains.Spring.BootAuthentication.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@RequestMapping("/loginCheck")
	public String checkDemo() {
	String	text = "this is the private page";
	text+="this page is not allowed to unauthenticated user";
		return text;
		
	}

}
