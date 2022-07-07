package com.kosta.myapp;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.myapp.repository.EnumTypeRepository;
import com.kosta.myapp.vo.MemberRoleEnum;
import com.kosta.myapp.vo.multikey.EnumTypeVO;

@SpringBootTest
public class EnumTypeTest {
	@Autowired
	EnumTypeRepository eRepo;
	
	@Test
	public void insert() {
		Set<MemberRoleEnum> mroleSet = new HashSet<>();
		mroleSet.add(MemberRoleEnum.ADMIN);
		mroleSet.add(MemberRoleEnum.USER);
		mroleSet.add(MemberRoleEnum.MANAGER);
		
		EnumTypeVO evo = EnumTypeVO.builder()
				.mid("swallow")
				.mname("H")
				.mpassword("1234")
				.mrole(mroleSet)
				.build();
		
		eRepo.save(evo);
	}
}
