package com.kosta.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kosta.myapp.repository.BoardRepository;

@Controller
public class JSPTestController {
	
	@Autowired
	BoardRepository brepo;
	
	@GetMapping("/hello")
	public String test1(Model model) {
		
		model.addAttribute("list", brepo.findAll());
		
		return "1";
	}
}
