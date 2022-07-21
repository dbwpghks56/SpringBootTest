package com.kosta.myapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.kosta.myapp.repository.FreeBoardRepliesRepository;
import com.kosta.myapp.repository.FreeBoardRepository;
import com.kosta.myapp.vo.relation.FreeBoard;
import com.kosta.myapp.vo.relation.FreeBoardReply;

@SpringBootTest
@WebAppConfiguration
@Commit
public class FreeBoardReplyTest {

	@Autowired
	FreeBoardRepository bRepo;
	
	@Autowired
	FreeBoardRepliesRepository replyRepo;
	
	@Test
	public void findreply() {
		bRepo.getCountReply().forEach(obj -> {
			System.out.println(Arrays.toString(obj));
		});
	}
	
	//@Test
	public void find() {
		Pageable page = PageRequest.of(0, 3);
		Page<FreeBoard> result = bRepo.findByBnoGreaterThan(266L, page);
		System.out.println(result.getNumber());
		System.out.println(result.getTotalPages());
		System.out.println(result.getNumberOfElements());
		System.out.println(result.getTotalElements());
		
		result.getContent().forEach(board->{
			System.out.println(board);
		});
	}
	
	//@Test
	public void getReplyCount() {
		bRepo.findById(286L).ifPresentOrElse(board->{
			System.out.println("댁슬 수 : "+ board.getReplies().size());
		}, null);
	}
	
	//@Test
	public void insertReply2() {
		bRepo.findById(286L).ifPresentOrElse(board->{
			FreeBoardReply reply1 = FreeBoardReply.builder()
					.reply("!!!!!")
					.replyer("Hwan")
					.board(board)
					.build();
			
			replyRepo.save(reply1);
		},()->{
			System.out.println("없으");
		});
	}
	
	// 보드찾아서 댓글 넣기
	//@Transactional // fetch가 LAZY인 경우에 넣어줘 commit 해줘야된다.
	//@Test
	public void insertReply() {
		bRepo.findById(286L).ifPresentOrElse(board->{
			List<FreeBoardReply> replies = board.getReplies();
			if(replies == null) {
				replies = new ArrayList<>();
			}
			
			FreeBoardReply reply1 = FreeBoardReply.builder()
					.reply("!!!!!")
					.replyer("Hwan")
					.board(board)
					.build();
			
			FreeBoardReply reply2 = FreeBoardReply.builder()
					.reply("$$$$$$")
					.replyer("Hwan")
					.board(board)
					.build();
			
			replies.add(reply1);
			replies.add(reply2);
			
			bRepo.save(board);
		}, ()->{
			System.out.println("안 찾아짐");
		});
	}
	
	//@Test
	public void selectAll() {
		bRepo.findAll().forEach(board->{
			System.out.println(board);
		});
	}
	
	//@Test
	public void insertBoard() {
		IntStream.rangeClosed(1, 10).forEach(i->{
			FreeBoard board = FreeBoard.builder()
					.title("댓글 없는 제목:"+i)
					.writer("작성자:"+i)
					.content("내용이당:"+i)
					.build();
			
			bRepo.save(board);
		});
	}
	
	//@Test
	public void insert() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			FreeBoard board = FreeBoard.builder()
					.title("제목:" + i)
					.writer("작가:" + i)
					.content("내용:" +i)
					.build();
			
			List<FreeBoardReply> boardreplies = new ArrayList<>();
			IntStream.rangeClosed(1, 5).forEach(j->{
				FreeBoardReply reply = FreeBoardReply.builder()
						.reply("reply:"+j)
						.replyer("replyer:"+i)
						.board(board) // 이렇게 해야 참조값이 제대로 들어간다.
						.build();
				boardreplies.add(reply);
			});
			
			board.setReplies(boardreplies);
			
			bRepo.save(board);
		});
	}
}



















