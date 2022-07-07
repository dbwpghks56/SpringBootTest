package com.kosta.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosta.myapp.repository.WebBoardRepository;
import com.kosta.myapp.repository.WebReplyRepository;

@Controller
@RequestMapping("/board/*")
public class WebBoardController {
	
	@Autowired
	WebBoardRepository boardRepo;
	
	@Autowired
	WebReplyRepository reRepo;
	
	@GetMapping("/repliesboard.go")
	public String replyToBoard( Long bno, Model model) {
		model.addAttribute("replies", reRepo.getRepliesOfBoard(boardRepo.findById(bno).get()));
		
		return "board/repliesboard";
	}
	
	@GetMapping("/boardlist.go")
	public String boardlist(Model model) {
		
		model.addAttribute("blist", boardRepo.findAll());
		
		return "board/boardlist";
	}
}
