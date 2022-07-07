package com.kosta.myapp;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.kosta.myapp.repository.BoardRepository;
import com.kosta.myapp.vo.BoardVO;

@SpringBootTest
public class BoardTest {
	@Autowired
	BoardRepository boardRepo;
	
	@Test
	public void jpqltest() {
//		boardRepo.selectAllByTitle("제목", 50L).forEach((b) -> {
//			System.out.println(b);
//		});
	}
	
	//@Test
	public void pagingInfo() {
		Timestamp s = Timestamp.valueOf("2022-06-29 00:00:00");
		Timestamp e = Timestamp.valueOf("2022-06-30 00:00:00");
		Pageable paging = PageRequest.of(1, 5, Sort.by(Direction.DESC, "writer", "regDate"));
		
		Page<BoardVO> result = boardRepo.findByRegDateBetween(s, e, paging);
		
		System.out.println(result.getNumber());
		System.out.println(result.getTotalElements());
		System.out.println(result.getTotalPages());
		System.out.println(result.getNumberOfElements());
		
		result.getContent().forEach((b) -> {
			System.out.println(b);
		});
	}
	
	//@Test
	public void pagingSort() {
		Timestamp s = Timestamp.valueOf("2022-06-29 00:00:00");
		Timestamp e = Timestamp.valueOf("2022-06-30 00:00:00");
		Pageable paging = PageRequest.of(1, 5, Sort.by(Direction.DESC, "writer", "regDate"));
		
		boardRepo.findByRegDateBetweenOrderByBnoDesc(s, e, paging).forEach((b) -> {
			System.out.println(b);
		});
	}
	
	//@Test
	public void test6() {
		boardRepo.findByTitleContaining("제목").forEach((board) -> {
			System.out.println(board);
		});
	}
	
	//@Test
	public void test5() {
		boardRepo.findByTitle("수정 제목").forEach((board) -> {
			System.out.println(board);
		});
	}
	
	//@Test
	public void test4() {
		boardRepo.findById(100L).ifPresentOrElse((board) -> {
			board.setTitle("수정 제목");
			board.setContent("수정 내용");
			board.setWriter("내가 수정");
			
			boardRepo.save(board); // 위에 찾아내는 id에 해당하는 값이 있으면 수정
		}, () -> {
			BoardVO board = BoardVO.builder()
					.title("제목 new")
					.content("내용 new")
					.writer("Ryu new")
					.build();
			
			boardRepo.save(board); // 위에 찾아내는 id에 해당하는 값이 없으면 insert
		});
	}
	
	//@Test
	public void test3() {
		Optional<BoardVO> board = boardRepo.findById(101L); // select where
		if(board.isPresent()) {
			BoardVO bget = board.get();
			System.out.println(bget);
		} else {
			System.out.println("Nothing");
		}
//		boardRepo.findById(101L).ifPresentOrElse((board) -> {
//			System.out.println(board);
//		}, () -> {
//			System.out.println("없엉");
//		});
	}
	
	//@Test
	public void test2() {
		List<BoardVO> boardlist = (List<BoardVO>) boardRepo.findAll(); // select *
		boardlist.forEach(board -> {
			System.out.println(board);
		});
	}
	
	//@Test
	public void test1() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			BoardVO board = BoardVO.builder()
					.title("제목" + i)
					.content("내용" + i)
					.writer("Ryu")
					.build();
			
			boardRepo.save(board); // insert
		});
	}
}
