package com.kosta.myapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.myapp.repository.WebBoardRepository;
import com.kosta.myapp.repository.WebReplyRepository;
import com.kosta.myapp.vo.relation.WebBoard;
import com.kosta.myapp.vo.relation.WebReply;

@RestController
@RequestMapping("/replies/*")
public class WebReplyRestController {
	
	@Autowired
	WebReplyRepository reRepo;
	
	@Autowired
	WebBoardRepository bRepo;
	
	@GetMapping("/{bno}")
	public List<WebReply> replies(@PathVariable Long bno) {
		WebBoard board = WebBoard.builder()
				.bno(bno)
				.title("d")
				.build();
		
		return reRepo.findByBoard(board);
	}
	
	@PostMapping("/{bno}")
	public List<WebReply> insert(@PathVariable Long bno, @RequestBody WebReply reply) {
		WebBoard board2 = bRepo.findById(bno).get();
		
		reply.setBoard(board2);
		
		reRepo.save(reply);
		
		WebBoard board = WebBoard.builder()
				.bno(bno)
				.title("d")
				.build();
		
		return reRepo.findByBoard(board);
	}
	
	@PutMapping("/{bno}")
	public List<WebReply> update(@PathVariable Long bno, @RequestBody WebReply reply) {
		WebBoard board2 = bRepo.findById(bno).get();
		
		reply.setBoard(board2);
		
		reRepo.save(reply);
		
		WebBoard board = WebBoard.builder()
				.bno(bno)
				.title("d")
				.build();
		
		return reRepo.findByBoard(board);
	}
	
	@DeleteMapping("/{bno}/{rno}")
	public List<WebReply> remove(@PathVariable Long bno, @PathVariable Long rno) {
		
		reRepo.delete(reRepo.findById(rno).get());
		
		return reRepo.getRepliesOfBoard2(bno);
	}
}



















