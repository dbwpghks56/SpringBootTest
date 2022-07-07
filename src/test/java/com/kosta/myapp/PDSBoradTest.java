package com.kosta.myapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.myapp.repository.PDSBoardRepository;
import com.kosta.myapp.repository.PDSFileRepository;
import com.kosta.myapp.vo.relation.PDSBoard;
import com.kosta.myapp.vo.relation.PDSFile;

import lombok.extern.java.Log;

@SpringBootTest
@Log
public class PDSBoradTest {
	
	@Autowired
	PDSBoardRepository bRepo;
	
	@Autowired
	PDSFileRepository fileRepo;
	
	@Test
	public void fileInfo() {
		bRepo.getFilesInfo().forEach(object->{
			System.out.println(Arrays.toString(object));
		});
	}
	
	//@Test
	public void fileSearch() {
		fileRepo.findById(141L).ifPresentOrElse(file -> {
			System.out.println(file);
		}, ()->{
			System.out.println("없으");
		});
	}
	
	//@Test
	public void retrieveById() {
		bRepo.findById(146L).ifPresentOrElse(board->{
			log.info(board.toString());
		}, () -> {
			log.info("없엉");
		});
	}
	
	//@Test
	public void retrieve() {
		bRepo.findAll().forEach(board -> {
			System.out.println(board);
		});
	}
	
	//@Test
	public void insert() {
		IntStream.rangeClosed(1, 3).forEach(i -> {
			PDSBoard board = PDSBoard.builder()
					.pname("메뉴얼:"+i)
					.pwriter("전우치:" + i)
					.build();
			
			List<PDSFile> filelist = new ArrayList<>();
			IntStream.rangeClosed(1, 5).forEach(j->{
				PDSFile file = PDSFile.builder()
						.pdsfilename("파일이름:"+j+".doc")
						.build();
				filelist.add(file);
			});
			
			board.setFiles2(filelist);
			
			bRepo.save(board);
		});
	}
}


















