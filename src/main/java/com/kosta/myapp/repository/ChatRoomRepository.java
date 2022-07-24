package com.kosta.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.kosta.myapp.vo.ChatRoomDTO;

@Repository
public interface ChatRoomRepository extends PagingAndSortingRepository<ChatRoomDTO, String> {
	
	public ChatRoomDTO findByRoomId(String roomId);
	
	@Query(value = "select * from tbl_room r where r.mid = ?1 or r.target = ?1", nativeQuery = true)
	public List<ChatRoomDTO> findByTargetOrId(String mid);
}
