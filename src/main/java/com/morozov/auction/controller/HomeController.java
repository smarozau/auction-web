package com.morozov.auction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("home")
public class HomeController {
	
	@RequestMapping(path="/home", method=RequestMethod.GET)
	public String show() {
		return "home";
	}

}
