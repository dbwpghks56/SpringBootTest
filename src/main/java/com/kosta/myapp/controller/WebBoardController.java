package com.kosta.myapp.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kosta.myapp.repository.MemberRepository;
import com.kosta.myapp.repository.WebBoardRepository;
import com.kosta.myapp.repository.WebReplyRepository;
import com.kosta.myapp.security.MemberService;
import com.kosta.myapp.vo.PageMaker;
import com.kosta.myapp.vo.PageVO;
import com.kosta.myapp.vo.relation.WebBoard;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/board/*")
public class WebBoardController {
	
	@Autowired
	WebBoardRepository boardRepo;
	
	@Autowired
	WebReplyRepository reRepo;
	
	@Autowired
	MemberService mservice;
	
	@GetMapping("/repliesboard.go")
	public String replyToBoard( Long bno, Model model) {
		model.addAttribute("replies", reRepo.getRepliesOfBoard(boardRepo.findById(bno).get()));
		
		return "board/repliesboard";
	}
	
	@GetMapping("/boardlist.go")
	public String boardlist(@ModelAttribute PageVO pageVO , Model model, Principal principal, Authentication authentication,
			HttpSession session) {
		Pageable page = pageVO.makePaging(0, "bno");
		Predicate predicate = boardRepo.makePredicate(pageVO.getType(), pageVO.getKeyword());
		
		System.out.println(principal);
		System.out.println((UserDetails) authentication.getPrincipal());
		
		UserDetails member = (UserDetails)session.getAttribute("user");
		System.out.println("방법0:" + member);
		System.out.println("방법1:" + principal);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		System.out.println("방법2:" + userDetails);
		Object principal2 = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		UserDetails userDetails2 = (UserDetails)principal2; 
		System.out.println("방법3:" + userDetails2);
	
		String mid = principal.getName();
		UserDetails userDetails3 = mservice.loadUserByUsername(mid);
		System.out.println("방법4:" +userDetails3);
		
		Page<WebBoard> blist =  boardRepo.findAll(predicate, page);
		
		model.addAttribute("blist", new PageMaker<>(blist));
		
		return "board/boardlist";
	}
	
	@GetMapping("/view.go")
	public String view(@ModelAttribute PageVO pageVO, long bno, Model model ) {
		
		System.out.println(bno);
		
		model.addAttribute("board", boardRepo.findById(bno).get());
		
		return "board/boardview";
	}
	
	@GetMapping("/register.go")
	public void register() {
		// 반환 타입이 void이면 함수의 이름으로 주소를 반환한다.
		
	}
	
	@PostMapping("/register.go")
	public String registerPost(WebBoard board, RedirectAttributes reattr) {
		
		boardRepo.save(board);
		reattr.addFlashAttribute("msg", "insert success");
		
		return "redirect:/board/boardlist.go";
	}
	
	@GetMapping("/modify.go")
	public String modify(Model model, long bno, @ModelAttribute PageVO pageVO) {
		
		model.addAttribute("board", boardRepo.findById(bno).get());
		
		return "board/modify";
	}
	
	@PostMapping("/modify.go") 
	public String modifyupdate(WebBoard board, PageVO pageVO, RedirectAttributes reattr) {
		boardRepo.findById(board.getBno()).ifPresentOrElse(board2 -> {
			board2.setTitle(board.getTitle());
			board2.setContent(board.getContent());
			boardRepo.save(board2);
			
			reattr.addFlashAttribute("msg", "update success");
		}, () -> {
			System.out.println("수정 실패 : 수정할 데이터가 없음");
		});
		
		return "redirect:/board/boardlist.go";
	}
	
	@PostMapping("/delete.go") 
	public String modifydelete(WebBoard board, PageVO pageVO, RedirectAttributes reattr) {
		boardRepo.findById(board.getBno()).ifPresentOrElse(board2 -> {
			boardRepo.delete(board2);
			reattr.addFlashAttribute("msg", "delete success");
		}, () -> {
			System.out.println("삭제 실패 : 삭제할 데이터가 없음");
		});
		
		return "redirect:/board/boardlist.go";
	}
}


















