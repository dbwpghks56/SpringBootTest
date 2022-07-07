package com.kosta.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kosta.myapp.vo.relation.PDSBoard;

public interface PDSBoardRepository extends CrudRepository<PDSBoard, Long> {

	@Query("select b.pname, b.pwriter, f.pdsfilename " + " from PDSBoard b left outer join b.files2 f "
			+ "order by b.pid ")
	List<Object[]> getFilesInfo();

	@Query("select b.pname, count(f ) " + " from PDSBoard b left outer join b.files2 f    where b.pid>0 "
			+ " group by b.pname order by b.pname ")
	List<Object[]> getFilesCount();

	@Query(value = "select b.pname, count(*) from tbl_pdsboard b "
			+ " left outer join tbl_pdsfiles f on(b.pid=f.pdsno) group by b.pname order by 1 ", nativeQuery = true)
	List<Object[]> getFilesCount2();

	@Modifying
	@Query("UPDATE FROM PDSFile f set f. pdsfilename = ?2 WHERE f.fno = ?1 ")
	int updatePDSFile(Long fno, String newFileName);
}
