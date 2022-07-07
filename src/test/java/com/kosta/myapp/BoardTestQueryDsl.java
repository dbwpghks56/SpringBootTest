package com.kosta.myapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.kosta.myapp.repository.BoardRepository;
import com.kosta.myapp.vo.BoardVO;
import com.kosta.myapp.vo.QBoardVO;
import com.querydsl.core.BooleanBuilder;

@SpringBootTest
public class BoardTestQueryDsl {

	@Autowired
	BoardRepository boardRepo;
	
	@Test
	public void testPredicate() {
		String type = "content";
		String keyword = "12";

		BooleanBuilder builder = new BooleanBuilder();
		QBoardVO board = QBoardVO.boardVO;
		if (type.equals("content")) {
			builder.and(board.content.like("%" + keyword + "%"));
		} else if(type.equals("title")) {
			builder.and(board.title.like("%"+keyword+"%"));
		}
		builder.and(board.bno.gt(10L)); // bno >10
		System.out.println(builder);
		Pageable pageable = PageRequest.of(0, 5);
		Page<BoardVO> result = boardRepo.findAll(builder, pageable);
		System.out.println("getSize:" + result.getSize());
		System.out.println("getTotalElements:" + result.getTotalElements());
		System.out.println("getTotalPages:" + result.getTotalPages());
		System.out.println("nextPageable:" + result.nextPageable());
		result.getContent().forEach(evo -> {
			System.out.println(evo);
		});
	}
}










