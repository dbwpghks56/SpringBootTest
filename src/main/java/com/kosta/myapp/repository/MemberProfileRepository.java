package com.kosta.myapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kosta.myapp.vo.MemberDTO;
import com.kosta.myapp.vo.ProfileDTO;

public interface MemberProfileRepository extends CrudRepository<ProfileDTO, Long> {
	List<ProfileDTO> findByMember(MemberDTO member);
}
