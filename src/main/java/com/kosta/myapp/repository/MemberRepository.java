package com.kosta.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kosta.myapp.vo.MemberDTO;

public interface MemberRepository extends CrudRepository<MemberDTO, String> {
	
	// JPQL native query (sql)
	@Query(value = "select m.mid, count(p.member_mid)\n"
			+ " from tbl_members m left outer join tbl_profile p\n"
			+ " on(m.mid = p.member_mid)\n"
			+ " where m.mid like %?1%\n"
			+ " group by m.mid\n", nativeQuery = true)
	List<Object[]> getMemberWithProfileCount(String mid);
	
	// JPQL
	@Query(value = "select m.mid, count(p)\n"
			+ " from MemberDTO m left outer join ProfileDTO p\n"
			+ "on ( m.mid = p.member )\n"
			+ " where m.mid like %?1%\n"
			+ " group by m.mid")
	List<Object[]> getMemberWithProfileCount2(String mid);
}
