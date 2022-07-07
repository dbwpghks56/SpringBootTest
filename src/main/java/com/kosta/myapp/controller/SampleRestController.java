package com.kosta.myapp.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.myapp.repository.BoardRepository;
import com.kosta.myapp.repository.CarRepository;
import com.kosta.myapp.vo.BoardVO;
import com.kosta.myapp.vo.CarVO;

@RestController
public class SampleRestController {
	@Autowired
	CarRepository carRepo;
	
	@Autowired
	BoardRepository boardRepo;
	
	@GetMapping("/boardlist.do/{pageno}")
	public List<BoardVO> pageInfo(@PathVariable int pageno) {
		Timestamp s = Timestamp.valueOf("2022-06-29 00:00:00");
		Timestamp e = Timestamp.valueOf("2022-06-30 00:00:00");
		Pageable paging = PageRequest.of(pageno, 5, Sort.by(Direction.DESC, "writer", "regDate"));
		
		Page<BoardVO> result = boardRepo.findByRegDateBetween(s, e, paging);
		
		System.out.println(result.getNumber());
		System.out.println(result.getTotalElements());
		System.out.println(result.getTotalPages());
		System.out.println(result.getNumberOfElements());
		
		result.getContent().forEach((b) -> {
			System.out.println(b);
		});
		
		return result.getContent();
	}
	
	@GetMapping("/carlist.do")
	public List<CarVO> getCarList() {
		List<CarVO> carlist = (List<CarVO>) carRepo.findAll();
		
		return carlist;
	}
}















