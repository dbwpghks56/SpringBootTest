package com.kosta.myapp.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import com.kosta.myapp.vo.BoardVO;

public interface BoardRepository extends CrudRepository<BoardVO, Long>, QuerydslPredicateExecutor<BoardVO> {
	// 1. 기본 CRUD는 제공된다....findAll, findById, save, delete, count, exists 등등
	// 2. 규칙에 맞는 메서드 정의 추가
	// https://docs.spring.io/spring-data/jpa/docs/2.5.1/reference/html/#jpa.query-methods 규칙 찾아보기
	
	List<BoardVO> findByTitle(String title);
	List<BoardVO> findByWriter(String writer);
	List<BoardVO> findByWriterAndTitle(String writer, String title);
	List<BoardVO> findByTitleLike(String title); // 내가 값을 줄 때 %를 포함해서 줘야함
	List<BoardVO> findByTitleContaining(String title);
	List<BoardVO> findByBnoGreaterThan(Long bno); // 이 bno보다 큰것들 조회
	List<BoardVO> findByBnoGreaterThanOrderByBnoDesc(Long bno);
	List<BoardVO> findByregDateAfter(Timestamp regdate);
	List<BoardVO> findByRegDateBetweenOrderByBnoDesc(Timestamp sdate, Timestamp edate, Pageable paging);
	
	Page<BoardVO> findByRegDateBetween(Timestamp sdate, Timestamp edate, Pageable paging);
	
	// 3. JPQL(JPA Query Language) ... join, 복잡 SQL가능
//	@Query("select b from BoardVO b where b.bno >= ?2 and b.title like %:tt% order by b.title desc")
//	List<BoardVO> selectAllByTitle( @Param("tt") String title, Long bno);
//	
//	@Query("select b from BoardVO b where b.bno >= ?2 and b.title like %?1% order by b.title desc")
//	List<BoardVO> selectAllByTitle2( String title, Long bno);
//	
//	@Query("select b from #{#entityName} b where b.bno >= ?2 and b.title like %?1% order by b.title desc")
//	List<BoardVO> selectAllByTitle3( String title, Long bno);
}
















