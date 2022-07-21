package com.kosta.myapp.repository;

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.kosta.myapp.vo.ChatRoomDTO;
import com.kosta.myapp.vo.relation.WebReply;

@Repository
public interface ChatRoomRepository extends PagingAndSortingRepository<ChatRoomDTO, String> {
	
	public ChatRoomDTO findByRoomId(String roomId);
}
