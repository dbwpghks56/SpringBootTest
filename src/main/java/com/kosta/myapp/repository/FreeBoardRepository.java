package com.kosta.myapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.kosta.myapp.vo.relation.FreeBoard;

public interface FreeBoardRepository extends PagingAndSortingRepository<FreeBoard, Long> {

	Page<FreeBoard> findByBnoGreaterThan(Long bno, Pageable page);
	
	// @Modifying 쿼리문 안에 update, insert, create, delete 등을 쓰려면 이걸 써줘야 된다.
	@Query(value = "select bno, count(reply.board_bno)\n"
			+ "from tbl_freeboards board left outer join tbl_free_replies reply on(board.bno = reply.board_bno)\n"
			+ "group by bno", nativeQuery = true)
	List<Object[]> getCountReply();
}
