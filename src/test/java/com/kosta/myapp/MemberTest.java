package com.kosta.myapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.myapp.repository.MemberRepository;
import com.kosta.myapp.vo.MemberDTO;
import com.kosta.myapp.vo.MemberRoleEnum;

@SpringBootTest
public class MemberTest {
	@Autowired
	MemberRepository mRepo;
	
	@Test
	public void countMP() {
//		List<Object[]> plist = mRepo.getMemberWithProfileCount("wpghks:");
		List<Object[]> plist = mRepo.getMemberWithProfileCount2("wpghks:");
		
		plist.forEach(objArray -> {
			System.out.println(Arrays.toString(objArray));
		});
	}
	
	//@Test
	public void selectAll() {
		mRepo.findAll().forEach((member) -> {
			System.out.println(member);
		});
	}
	
	//@Test
	public void insert() {
		IntStream.rangeClosed(1, 5).forEach((index) -> {
			MemberDTO member = MemberDTO.builder()
					.mid("wpghks:"+index)
					.mname("멤버:"+index)
					.mpassword("1234")
					.mrole(index%2==0?MemberRoleEnum.ADMIN:MemberRoleEnum.USER)
					.build();
			mRepo.save(member);
		});
	}
}
