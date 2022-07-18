package com.kosta.myapp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kosta.myapp.repository.WebBoardRepository;
import com.kosta.myapp.repository.WebReplyRepository;
import com.kosta.myapp.vo.relation.WebBoard;
import com.kosta.myapp.vo.relation.WebReply;

@SpringBootTest
@WebAppConfiguration
public class WebBoardTest {

	@Autowired
	WebBoardRepository wRepo;
	
	@Autowired
	WebReplyRepository rRepo;
	
	
	@Test
	public void insert2() {
		IntStream.rangeClosed(1, 20).forEach(i->{
			WebBoard board = WebBoard.builder()
					.title("제목:"+i)
					.writer("작가:" + i % 5)
					.content("내용" + i)
					.build();
			
			wRepo.save(board);
		});
	}
	
	//@Test
	public void insert() {
		IntStream.rangeClosed(1, 50).forEach(i->{
			WebBoard board = WebBoard.builder()
					.title("제목:"+i)
					.writer("작가:" + i % 5)
					.content("내용" + i)
					.build();
			
			List<WebReply> replies = new ArrayList<>();
			IntStream.range(1, 10).forEach(j->{
				WebReply reply = WebReply.builder()
						.replyText("댓 내용:" + j % 5)
						.replyer("작성자:" + i % 5)
						.board(board)
						.build();
				
				replies.add(reply);
			});
			
			board.setReplies(replies);
			
			wRepo.save(board);
		});
	}
}


















