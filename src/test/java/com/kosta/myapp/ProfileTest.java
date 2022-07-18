package com.kosta.myapp;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kosta.myapp.repository.MemberProfileRepository;
import com.kosta.myapp.repository.MemberRepository;
import com.kosta.myapp.vo.MemberDTO;
import com.kosta.myapp.vo.ProfileDTO;

@SpringBootTest
@WebAppConfiguration
public class ProfileTest {
	@Autowired
	MemberProfileRepository proRepo;
	
	@Autowired
	MemberRepository mRepo;
	
	@Test
	public void selectOfMember() {
		mRepo.findById("wpghks:2").ifPresentOrElse((member)->{
			proRepo.findByMember(member).forEach(profile->{
				System.out.println(profile);
			});
		}, ()->{
			System.out.println("없는 것이여");
		});
	}
	
	//@Test
	public void select() {
		proRepo.findAll().forEach(profile -> {
			System.out.println(profile);
		});
	}
	
	//@Test
	public void insert() {
		Optional<MemberDTO> member = mRepo.findById("wpghks:4");
		
		if(member == null) {
			System.out.println("없는 거야");
			return;
		} 
		
		MemberDTO existMember = member.get();
		IntStream.rangeClosed(1, 10).forEach((index)->{
			ProfileDTO profile = ProfileDTO.builder()
					.fname("face" + index + "jpg")
					.current_yn(index==1?true:false)
					.member(existMember)
					.build();
			
			proRepo.save(profile);
		});
	}
}














