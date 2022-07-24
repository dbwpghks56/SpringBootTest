package com.kosta.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CameraConroller {
	@GetMapping("/camera")
	public void camera() {
		
	}
	
	@GetMapping("/cameras")
	public void cameras() {
		
	}
}
